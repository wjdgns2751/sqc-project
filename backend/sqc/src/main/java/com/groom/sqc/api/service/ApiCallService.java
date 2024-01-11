package com.groom.sqc.api.service;

import com.groom.sqc.api.ApiWebClient;
import com.groom.sqc.api.dto.ApiRequestDto;
import com.groom.sqc.dto.SqcRequestDto;
import com.groom.sqc.dto.SqcResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.http.HttpMethod.POST;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiCallService {

    private final ApiWebClient webClient;

    public void execute(SqcRequestDto sqcRequestDto, int count) {
        validate(count); // count 검증

        getTargets(count).boxed()
                .map(target -> createRequest(sqcRequestDto,target)) // API 호출에 사용할 요청 dto 세팅
                .forEach(webClient::call); // API 호출
    }

    private void validate(int count) {
        if (count < 1) throw new IllegalArgumentException("Greater than 1");
    }

    private ApiRequestDto<SqcResponseDto> createRequest(SqcRequestDto sqcRequestDto, Integer index) {
        return ApiRequestDto.<SqcResponseDto>builder()
                .returnType(SqcResponseDto.class) // 응답 바디 타입 지정
                .url("/api/call/api/v1/find-shoes") // 외부 가상 API URL
                .method(POST) // 외부 가상 API 메소드
                .body(createBody(sqcRequestDto,index)) // 요청 바디 생성
                .callback(this::callback)
                .build();
    }

    private void callback(SqcResponseDto response) {
        log.info("Success create item : {}", response); // 결과값 확인 위해 로그
    }

    private SqcRequestDto createBody(SqcRequestDto sqcRequestDto, Integer index) { // 요청 바디 생성
        return SqcRequestDto.builder()
                .name(sqcRequestDto.getName() + index)
                .tags(sqcRequestDto.getTags())
                .brand(sqcRequestDto.getBrand())
                .sex(sqcRequestDto.getSex())
                .size(sqcRequestDto.getSize())
                .minPrice(sqcRequestDto.getMinPrice())
                .maxPrice(sqcRequestDto.getMaxPrice())
                .build();
    }

    private IntStream getTargets(int count) {
        return IntStream.range(1, count + 1); // 1부터 파라미터로 들어온 count만큼 API 호출 하는 프로세스
    }
}