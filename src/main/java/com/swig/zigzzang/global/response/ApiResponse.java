package com.swig.zigzzang.global.response;

public record ApiResponse<T>(String success, T data) {
}
