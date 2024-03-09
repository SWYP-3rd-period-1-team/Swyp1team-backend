package com.swig.zigzzang.calender.dto.request;

import lombok.Builder;

@Builder
public record SupplementSaveRequest(
        String username,
        String password) {
}
