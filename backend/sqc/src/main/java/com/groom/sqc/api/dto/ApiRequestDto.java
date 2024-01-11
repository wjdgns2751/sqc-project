package com.groom.sqc.api.dto;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.function.Consumer;

@Data
public class ApiRequestDto<T> {

    private final String url; // API 호출 uri
    private final Object body; // API 요청 바디
    private final HttpMethod method; // API 요청 메소드
    private final Class<T> returnType; // API 응답 클래스 타입
    private final Consumer<T> callback; // API 응답 콜백 처리
    private final MediaType accept;

    @Builder
    public ApiRequestDto(String url, Object body, HttpMethod method, Class<T> returnType, Consumer<T> callback,MediaType accept) {
        this.url = url;
        this.body = body;
        this.method = method;
        this.returnType = returnType;
        this.callback = callback;
        this.accept = accept;
    }
}
