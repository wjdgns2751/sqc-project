package com.groom.sqc.domain.dto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SqcRequestDto {

    private String name;
    private List<String> tag;
    private String brand;
    private String sex;
    private Integer size;
    private Integer minPrice;
    private Integer maxPrice;


}
