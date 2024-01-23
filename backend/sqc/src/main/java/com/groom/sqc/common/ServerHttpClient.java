package com.groom.sqc.common;
import org.springframework.http.ResponseEntity;

public interface ServerHttpClient {
    ResponseEntity<String> sendPostRequest(String url, Object requestData);
}
