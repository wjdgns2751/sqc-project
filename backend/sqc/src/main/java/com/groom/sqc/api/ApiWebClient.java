package com.groom.sqc.api;

import com.groom.sqc.api.dto.ApiRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public // 빈으로 등록
class ApiWebClient {

    private final WebClient webClient;

    public ApiWebClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8080")
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON.toString()) //기본 헤더 CONTENT_TYPE을 JSON으로 설정
                .build();
    }
    /*
    외부에서 API를 호출할 때 사용하는 메서드입니다.
    제네릭 타입 T를 받는 ApiRequestDto를 매개변수로 받습니다.
    execute 메서드를 호출하여 Mono를 구독하고, 결과를 콜백에 전달합니다.

    execute(request):
    이 메서드는 외부 API에 대한 비동기 요청을 보내고, 결과를 Mono로 받아오는 역할을 합니다.
    execute 메서드는 webClient를 사용하여 외부 API에 요청을 보내고, 결과를 Mono로 변환하여 반환합니다.

    subscribe(request.getCallback()):
    Mono는 리액티브 스트림에서 비동기적으로 데이터를 처리하기 위한 타입입니다.
    subscribe 메서드는 Mono나 다른 리액티브 타입에서 데이터가 나오면 어떻게 처리할지를 정의합니다.
    request.getCallback()은 ApiRequestDto 객체에서 콜백을 얻어옵니다. 이 콜백은 외부 API 요청의 결과를 처리할 로직을 담고 있는 함수 또는 메서드입니다.
    따라서 subscribe(request.getCallback())는 Mono에서 비동기적으로 발생하는 데이터를 콜백 함수로 전달하여 결과를 처리하도록 합니다.
    */
    public <T> void call(ApiRequestDto<T> request) {
        execute(request).subscribe(request.getCallback());
    }
    /*
    실제로 WebClient를 사용하여 API를 호출하는 비동기 메서드입니다.
    method, uri, bodyValue 등을 설정하여 외부 API에 요청합니다.
    retrieve() 메서드를 사용하여 응답을 받아온 후, bodyToMono 메서드로 결과를 Mono로 변환합니다.
    반환된 Mono를 호출한 곳으로 반환합니다.

    Mono는 Project Reactor에서 제공하는 데이터 유형 중 하나입니다.
    Mono는 0 또는 1개의 항목을 처리할 수 있는 리액티브(Reactive) 프로그래밍에서 사용되는 타입입니다.
    비동기 작업의 결과물을 나타내며, 0개의 항목일 수도 있고, 1개의 항목일 수도 있습니다.
    Mono는 비동기적으로 데이터를 처리할 때 유용하며, 외부 리소스나 서비스와 상호 작용할 때 자주 사용됩니다.
    * */
    private <T> Mono<T> execute(ApiRequestDto<T> request) {
        return webClient.method(request.getMethod())
                .uri(request.getUrl())
                .bodyValue(request.getBody())
                .retrieve()
                .bodyToMono(request.getReturnType());
    }
}