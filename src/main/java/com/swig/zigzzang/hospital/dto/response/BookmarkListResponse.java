package com.swig.zigzzang.hospital.dto.response;

import com.swig.zigzzang.hospital.dto.util.BookmarkDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record BookmarkListResponse(
        String message,
        List<BookmarkDTO> bookmarkList
) {

    public static BookmarkListResponse of(List<BookmarkDTO> bookmarkDTOList) {
        return bookmarkDTOList.isEmpty() ?
                BookmarkListResponse.builder()
                        .message("찜한 병원이 없습니다.")
                        .build() :
                BookmarkListResponse.builder()
                        .bookmarkList(bookmarkDTOList)
                        .build();
    }
}
