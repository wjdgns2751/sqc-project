package com.groom.sqc.services;

import com.groom.sqc.common.ApiServerHttpClient;
import com.groom.sqc.domain.documents.SqcDocument;
import com.groom.sqc.domain.dto.SqcRequestDto;
import com.groom.sqc.domain.dto.SqcResponseDto;
import com.groom.sqc.repository.SqcDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class SqcService {
    private final SqcDocumentRepository sqcDocumentRepository;
    ApiServerHttpClient apiServerHttpClient;

    public List<SqcResponseDto> shoesList(SqcRequestDto sqcRequestDto) {
        // 1. 요청 들어온 데이터를 Python에게 전달 후 리턴값으로 몽고 DB에 저장된 키 값을 응답 받음
        ResponseEntity<String> responseEntity  = apiServerHttpClient.sendPostRequest("/findShoes",sqcRequestDto);
        log.info("response status : {}",responseEntity.getStatusCode());
        log.info("response value : {}",responseEntity.getBody());
        // 입력 문자열을 ":"를 기준으로 분리하여 id를 추출
        String body = responseEntity.getBody();


        assert body != null;
        String[] tokens = body.split(",");
        Iterable<Long> idRange = null;

        if (tokens.length == 2) {
            String startToken = tokens[0].split(":")[1].trim();
            String endToken = tokens[1].split(":")[1].trim();

            try {
                int startId = Integer.parseInt(startToken);
                int endId = Integer.parseInt(endToken);

                idRange = IntStream.rangeClosed(startId, endId)
                        .boxed()
                        .map(Long::valueOf)
                        .collect(Collectors.toList());

            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
        }

        // 2. 연결된 몽고 DB에서 키 값을 기준으로 조회후 List<SqcResponseDto>로 반환
        // SqcVo를 생성하고, 이를 이용하여 SqcResponseDto를 초기화합니다.

        // 몽고디비에서 데이터를 조회하고 SqcVo 리스트를 얻어옵니다.
        assert idRange != null;
        // ID 범위 안의 sqcDocument 를 sqcResponseDto 형식으로 변환 후 리스트화
        return sqcDocumentRepository.findAllById(idRange)
                .stream()
                .map(SqcResponseDto::new)
                .collect(Collectors.toList());


    }

    public SqcResponseDto shoes(Long id){
        Optional<SqcDocument> sqcDocument = sqcDocumentRepository.findById(id);
        return sqcDocument
                .map(SqcResponseDto::new)
                .orElseThrow(() -> new NotFoundException("SqcDocument not found with id: " + id));
     }

}
