package com.groom.sqc.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.groom.sqc.common.ServerProperties.PYTHON_HOST_NAME;

@Component("apiServerHttpClient")
@RequiredArgsConstructor
@Slf4j
public class ApiServerHttpClient implements ServerHttpClient {
    StringBuilder stringBuilder = new StringBuilder();

    HttpHeaders headers = new HttpHeaders();

    @Override
    public ResponseEntity<String> sendPostRequest(String url, Object requestData) {
        // Python 서버에 특화된 동작을 구현
        stringBuilder.append(PYTHON_HOST_NAME);
        stringBuilder.append(url);
        log.info("url {}", stringBuilder);
        // RestTemplate 을 사용하여 Python 서버로 POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.exchange(
                stringBuilder.toString(),
                HttpMethod.POST,
                new HttpEntity<>(requestData, headers),
                String.class
        );
    }
}
