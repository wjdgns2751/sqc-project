package com.groom.sqc.services;

import com.groom.sqc.common.ApiServerHttpClient;
import com.groom.sqc.domain.dto.SqcRequestDto;
import com.groom.sqc.domain.dto.SqcResponseDto;
import com.groom.sqc.repository.SqcDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SqcService {
    private final SqcDocumentRepository sqcDocumentRepository;
    private final ApiServerHttpClient apiServerHttpClient;


    public List<SqcResponseDto> shoesList(SqcRequestDto sqcRequestDto) {
        // 1. 요청 들어온 데이터를 Python에게 전달 후 리턴값으로 몽고 DB에 저장된 키 값을 응답 받음
        // 요청 파라미터가 존재하지 않으면 [ 전체 ] 존재하면 [ 검색]

        Optional<Object> optionalObject = Optional.ofNullable(sqcRequestDto);
        String url = optionalObject.isPresent() ? "/showShoesList" : "/findShoesList";


        log.info("url : {}",url);
        ResponseEntity<String> responseEntity = apiServerHttpClient.sendPostRequest(url, sqcRequestDto);
        String body = responseEntity.getBody();

        // 입력 문자열을 ":"를 기준으로 분리하여 id를 추출
        assert body != null;
        String[] tokens = body.split(",");
        String startToken = extractValue(tokens[0]);
        String endToken = extractValue(tokens[1]);

        ObjectId objectStart = new ObjectId(startToken);
        ObjectId objectEnd = new ObjectId(endToken);
        // 2. 연결된 몽고 DB에서 키 값을 기준으로 조회후 List<SqcResponseDto>로 반환
        // 몽고디비에서 데이터를 조회하고 SqcVo 리스트를 얻어옵니다.
        // ID 범위 안의 sqcDocument 를 sqcResponseDto 형식으로 변환 후 리스트화

    return sqcDocumentRepository.findByIdRange(objectStart, objectEnd)
            .stream()
            .map(SqcResponseDto::new)
            .collect(Collectors.toList());
    }

    public SqcResponseDto shoes(String id) {
        return sqcDocumentRepository.findById(id)
                .map(SqcResponseDto::new)
                .orElseThrow(() -> new NotFoundException("SqcDocument not found with id: " + id));
    }


    private static String extractValue(String token) {
        return Arrays.stream(token.split(":"))
                .skip(1)
                .findFirst()
                .map(String::trim)
                .orElse("");
    }

}
