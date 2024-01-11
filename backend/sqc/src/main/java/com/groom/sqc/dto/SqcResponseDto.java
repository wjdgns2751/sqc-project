package com.groom.sqc.dto;

import com.groom.sqc.vo.SqcVo;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SqcResponseDto {

    private SqcVo sqcVo;
    private LocalDateTime createdDate;

    @Builder
    public SqcResponseDto(SqcVo sqcVo,LocalDateTime createdDate) {
        this.sqcVo = sqcVo;
        this.createdDate = createdDate;
    }

    public static SqcResponseDto of(SqcResponseDto request) {
        return SqcResponseDto.builder()
                .sqcVo(request.getSqcVo())
                .createdDate(LocalDateTime.now())
                .build();
    }

}
