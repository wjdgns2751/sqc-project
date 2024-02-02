package com.groom.sqc.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("apiServerHttpClient")
@RequiredArgsConstructor
@Slf4j
public class ApiServerHttpClient implements ServerHttpClient {

    private final ServerProperties serverProperties ;
    StringBuilder stringBuilder = new StringBuilder();
    HttpHeaders headers = new HttpHeaders();

    @Override
    public ResponseEntity<String> sendPostRequest(String url, Object requestData) {
        stringBuilder.setLength(0);
        headers.add("Content-Type", "application/json");
        // Python 서버에 특화된 동작을 구현
        stringBuilder.append(serverProperties.getPythonHostName());
        stringBuilder.append(url);
        log.info("header {}", headers);
        log.info("url {}", stringBuilder);
        // RestTemplate 을 사용하여 Python 서버로 GET 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.exchange(
                stringBuilder.toString(),
                HttpMethod.GET,
                new HttpEntity<>(requestData, headers),
                String.class
        );
    }

}
