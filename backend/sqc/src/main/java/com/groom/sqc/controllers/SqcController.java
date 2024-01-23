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
    public List<SqcResponseDto> getShoesList(SqcRequestDto requestDto){
        return postShoesList(requestDto);
    }

    @PostMapping("/findShoesList")
    public List<SqcResponseDto> postShoesList(SqcRequestDto sqcRequestDto){
      return sqcService.shoesList(sqcRequestDto);
    }

    @GetMapping("/findShoes/id/{id}")
    @ResponseBody
    public SqcResponseDto getShoes(@PathVariable String id){
        return postShoes(id);
    }

    @PostMapping("/findShoes")
    public SqcResponseDto postShoes(String id){
        return sqcService.shoes(id);
    }

}
