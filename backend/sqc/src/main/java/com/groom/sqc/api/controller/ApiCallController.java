package com.groom.sqc.api.controller;
import com.groom.sqc.api.dto.ApiRequestDto;
import com.groom.sqc.api.service.ApiCallService;
import com.groom.sqc.dto.SqcRequestDto;
import com.groom.sqc.dto.SqcResponseDto;
import com.groom.sqc.vo.SqcVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/call")
public class ApiCallController {

    private final ApiCallService apiCallService;

    @GetMapping("/find-shoes/count/{count}") // {count}는 비동기로 호출할 개수
    public Mono<String> getShoesWithFilters(SqcRequestDto sqcRequestDto , @PathVariable(name = "count", required = false) int count) {
        log.info("서버 에서 응답드립니다. : {}", sqcRequestDto); // 결과값 확인을 위한 로그
        return Mono.fromRunnable(() -> apiCallService.execute(sqcRequestDto, count))
                .thenReturn("OK");
    }

    /* 테스트를 위해 만든 파이썬 역할 컨트롤러*/
    @PostMapping("/api/v1/find-shoes")
    public SqcResponseDto create(@RequestBody SqcRequestDto request) {
        log.info("Python 에서 응답드립니다. : {}", request.toString()); // 결과값 확인을 위한 로그

        SqcVo sqcVo = new SqcVo(request.getBrand(),"",null,"","","","","","");
        SqcResponseDto sqcResponseDto = new SqcResponseDto(sqcVo, LocalDateTime.now());

        return  SqcResponseDto.of(sqcResponseDto);
//        return "ok";
    }

}
