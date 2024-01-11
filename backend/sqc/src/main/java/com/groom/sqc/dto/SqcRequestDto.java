package com.groom.sqc.dto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class SqcRequestDto {

    private String name;
    private List<String> tags;
    private String brand;
    private String sex;
    private Integer size;
    private Integer minPrice;
    private Integer maxPrice;

    @Builder
    public SqcRequestDto(String name, List<String> tags, String brand, String sex, Integer size, Integer minPrice, Integer maxPrice) {
        this.name = name;
        this.tags = tags;
        this.brand = brand;
        this.sex = sex;
        this.size = size;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }


}
