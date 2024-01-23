package com.groom.sqc.domain.dto;

import com.groom.sqc.domain.documents.SqcDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class SqcResponseDto {
    private SqcDocument sqcDocument;
}
