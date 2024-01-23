package com.groom.sqc.domain.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class SqcRequestDto {

    private String name;
    private List<String> tags;
    private String brand;
    private String sex;
    private Integer size;
    private Integer minPrice;
    private Integer maxPrice;


}
