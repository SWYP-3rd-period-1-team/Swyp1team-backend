package com.swig.zigzzang.hospital.service;


import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.HospitalComment;
import com.swig.zigzzang.hospital.domain.MemberHospital;
import com.swig.zigzzang.hospital.dto.request.*;
import com.swig.zigzzang.hospital.dto.util.HospitalCommentDTO;
import com.swig.zigzzang.hospital.exception.HospitalNotExistException;
import com.swig.zigzzang.hospital.repository.HospitalCommentRepository;
import com.swig.zigzzang.hospital.repository.HospitalRepository;
import com.swig.zigzzang.hospital.repository.MemberHospitalRepository;
import com.swig.zigzzang.hospital.repository.QueryDsl.CustomHospitalCommentRepository;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.exception.CommentExistException;
import com.swig.zigzzang.member.exception.MemberNotExistException;
import com.swig.zigzzang.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    private final MemberHospitalRepository memberHospitalRepository;

    private final HospitalCommentRepository hospitalCommentRepository;

    private final CustomHospitalCommentRepository customHospitalCommentRepository;

    private final MemberRepository memberRepository;


    public void saveHospital(HospitalSaveRequest hospitalSaveRequest) { // 병원 등록

        Optional<Hospital> existingHospital = hospitalRepository.findByGoogleMapId(hospitalSaveRequest.getGoogleMapId());

        if (existingHospital.isPresent()) {
            return;
        }
        Hospital saveEntity = hospitalSaveRequest.toEntity();
        hospitalRepository.save(saveEntity);
    }


    @Transactional
    public void saveHospitalBookmark(HospitalBookmarkSaveRequest hospitalBookmarkSaveRequest) { // 북마크 등록 및 해제

        Hospital requestHospital = hospitalBookmarkSaveRequest.toHospitalEntity();

        Optional<Hospital> existingHospital = hospitalRepository.findByGoogleMapId(hospitalBookmarkSaveRequest.getGoogleMapId()); // 이미 등록된 병원인지 확인

        Optional<MemberHospital> optionalMemberHospital;

        Member loginMember = memberRepository.findByUserId(hospitalBookmarkSaveRequest.getUserId()).orElseThrow(MemberNotExistException::new);  // 로그인 ID 해당 회원 DB 저장 여부


        if (existingHospital.isEmpty()) {
            Hospital newHospital = hospitalRepository.saveAndFlush(requestHospital);  // 병원 등록이 안되어 있다면 지도 ID 기반 병원 정보 신규 저장
            optionalMemberHospital = memberHospitalRepository.findByUserIdAndHospitalId(hospitalBookmarkSaveRequest.getUserId(), newHospital.getHospitalId());
        } else {
            optionalMemberHospital = memberHospitalRepository.findByUserIdAndHospitalId(hospitalBookmarkSaveRequest.getUserId(), existingHospital.get().getHospitalId());

        }
        if (optionalMemberHospital.isPresent()) {
            // 기존 MemberHospital 객체가 있으면, 해당 객체를 수정
            MemberHospital existingMemberHospital = optionalMemberHospital.get();
            existingMemberHospital.setBookmark(hospitalBookmarkSaveRequest.getBookmark());
            memberHospitalRepository.save(existingMemberHospital);
        } else {
            // 기존 MemberHospital 객체가 없으면, 새로운 객체를 생성
            Hospital hospital = hospitalRepository.findByGoogleMapId(hospitalBookmarkSaveRequest.getGoogleMapId()).orElseThrow();
            MemberHospital newMemberHospital = hospitalBookmarkSaveRequest.toMemberHospitalEntity(loginMember, hospital);
            memberHospitalRepository.save(newMemberHospital);
        }
    }

    @Transactional
    public List<HospitalCommentDTO> findHospitalDetails(String googleMapId) { /// 병원 상세 조회

        List<HospitalComment> result = customHospitalCommentRepository.findHospitalCommentsByGoogleMapId(googleMapId);

        return convertNestedStructure(result);
    }


    @Transactional
    public void addComment(CommentInsertRequest commentInsertRequest) { // 리뷰 등록

        Member loginMember = memberRepository.findByUserId(commentInsertRequest.getUserId())
                .orElseThrow(MemberNotExistException::new);

        Hospital hospital = hospitalRepository.findByGoogleMapId(commentInsertRequest.getGoogleMapId())
                .orElseThrow(HospitalNotExistException::new);

        HospitalComment hospitalComment = commentInsertRequest.toEntity(loginMember, hospital);

        hospitalCommentRepository.save(hospitalComment);

    }

    @Transactional
    public void addChildComment(Long commentId, CommentInsertRequest commentInsertRequest) { // 리뷰&댓글에 대한 대댓글 등록

        Member loginMember = memberRepository.findByUserId(commentInsertRequest.getUserId())
                .orElseThrow(MemberNotExistException::new);

        Hospital hospital = hospitalRepository.findByGoogleMapId(commentInsertRequest.getGoogleMapId())
                .orElseThrow(HospitalNotExistException::new);

        HospitalComment parent = hospitalCommentRepository.findByHospitalCommentId(commentId)
                .orElseThrow(CommentExistException::new); // 부모 댓글 조회

        HospitalComment hospitalChildComment = commentInsertRequest.toChildEntity(loginMember, hospital, parent);

        hospitalCommentRepository.save(hospitalChildComment);

    }


    public void modifyComment(Long commentId, CommentUpdateRequest commentUpdateRequest) { // 댓글 수정

        Member loginMember = memberRepository.findByUserId(commentUpdateRequest.getUserId())
                .orElseThrow(MemberNotExistException::new);

        Hospital hospital = hospitalRepository.findByGoogleMapId(commentUpdateRequest.getGoogleMapId())
                .orElseThrow(HospitalNotExistException::new);

    }


    public void addGeneralChildComment(Long commentId, ChildCommentInsertRequest childCommentInsertRequest) { // 대댓글 등록

        Member loginMember = memberRepository.findByUserId(childCommentInsertRequest.getUserId())
                .orElseThrow(MemberNotExistException::new);


    }


    private List<HospitalCommentDTO> convertNestedStructure(List<HospitalComment> commentList) {
        List<HospitalCommentDTO> result = new ArrayList<>();
        Map<Long, HospitalCommentDTO> map = new HashMap<>();
        commentList.forEach(comment -> {
            HospitalCommentDTO hospitalCommentDTO = HospitalCommentDTO.of(comment);
            map.put(hospitalCommentDTO.getHospitalCommentId(), hospitalCommentDTO);
            if (comment.getParent() != null)
                map.get(comment.getParent().getHospitalCommentId()).getChildren().add(hospitalCommentDTO); // 부모 댓글이 있는 경우 부모 DTO의 댓글 리스트로 add
            else result.add(hospitalCommentDTO);
        });

        return result;
    }


}
