package com.swig.zigzzang.profile;

import static com.amazonaws.services.ec2.model.ResourceType.Image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.profile.exception.ImageUploadException;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucket;

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }
    public String saveProfileImage(MultipartFile profileImage) throws IOException {
        String originalFilename = profileImage.getOriginalFilename();
        String storedFileName = generateUniqueFileName(originalFilename);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(profileImage.getSize());
        metadata.setContentType(profileImage.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucket, storedFileName, profileImage.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3.getUrl(bucket, storedFileName).toString();
    }
    public String saveProfileImage(String userId, MultipartFile profileImage) throws IOException {
        String folderName = userId + "/";  // 사용자별 폴더 생성
        String originalFilename = profileImage.getOriginalFilename();
        String storedFileName = folderName + generateUniqueFileName(originalFilename);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(profileImage.getSize());
        metadata.setContentType(profileImage.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucket, storedFileName, profileImage.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3.getUrl(bucket, storedFileName).toString();

    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }
    public void deleteProfileImage(Member member) {


        String profileImage = member.getProfileimage();
        if (profileImage != null) {
//            String key = profileImage.substring(profileImage.lastIndexOf("/") + 1);
            String key = extractString(profileImage, member.getUserId());
            amazonS3.deleteObject(bucket, key);
        }
    }
    private static String extractString(String input, String keyword) {
        //사용자명 폴더 포함한 key 값반환하는함수 구현
        int keywordIndex = input.indexOf(keyword);
        if (keywordIndex != -1) {
            return input.substring(keywordIndex);
        }
        return null;
    }



}
