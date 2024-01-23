package com.groom.sqc.api.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureWebClient
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApiCallControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void getShoesWithFilters() {
        //List<String> tags = Arrays.asList("running", "comfort");

        webTestClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/api/call/find-shoes/count/3")
                        .queryParam("brand", "Nike")
                        .queryParam("minPrice", 50)
                        //.queryParam("tags", tags)
                        .build())
                .exchange()
                .expectStatus().isOk() // 응답 코드 기대값
                .expectBody(String.class) // 응답 body 클래스 타입 기대값
                .value(response -> { // 응답 바디 response
                    System.out.println("response = " + response);
                    assertThat(response).isEqualToIgnoringCase("hello world ");
                });
    }
}