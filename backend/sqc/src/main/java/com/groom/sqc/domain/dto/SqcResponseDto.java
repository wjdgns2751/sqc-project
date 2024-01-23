package com.groom.sqc.domain.dto;

import com.groom.sqc.domain.documents.SqcDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class SqcResponseDto {
    private SqcDocument sqcDocument;
    public SqcResponseDto(SqcDocument sqcDocument) {
        this.sqcDocument = sqcDocument;
    }


}
