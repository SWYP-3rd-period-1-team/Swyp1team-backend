package com.swig.zigzzang.global.response;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class HttpResponse<T> extends ResponseEntity<ApiResponse<T>> {

    public static <T> HttpResponse<T> okBuild(T body) {
        return new HttpResponse<>(HttpStatus.OK, body);
    }
    public record HttpResponseBuilder(HttpStatusCode status) {
        public <T> HttpResponse<T> body(T body) {
            return new HttpResponse<T>(status, body);
        }

        public <T> HttpResponse<T> build() {
            return new HttpResponse<>(status);
        }
    }

    public static HttpResponseBuilder status(HttpStatus status) {
        return new HttpResponseBuilder(status);
    }
    public HttpResponse(HttpStatusCode status) {
        super(status);
    }

    public HttpResponse(ApiResponse<T> body, HttpStatusCode status) {
        super(body, status);
    }
    public HttpResponse(HttpStatusCode status, T data) {
        super(new ApiResponse<>("true", data), status);
    }



}
