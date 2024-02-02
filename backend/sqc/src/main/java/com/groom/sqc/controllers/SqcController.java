package com.groom.sqc.controllers;

import com.groom.sqc.domain.dto.SqcRequestDto;
import com.groom.sqc.domain.dto.SqcResponseDto;
import com.groom.sqc.services.SqcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Shoes Quick Comparison
* */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SqcController {
    private final SqcService sqcService;


    @GetMapping("/findShoesList")
    @ResponseBody
    public List<SqcResponseDto> findShoesList(SqcRequestDto requestDto){
        return sqcService.shoesList(requestDto);
    }

    @GetMapping("/findShoes/id/{id}")
    @ResponseBody
    public SqcResponseDto getShoes(@PathVariable String id){
        return sqcService.shoes(id);
    }


}
