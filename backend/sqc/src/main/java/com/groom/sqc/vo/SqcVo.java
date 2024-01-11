package com.groom.sqc.vo;
import java.util.List;
/*
불변(Immutable)하고 데이터를 저장하기 위한 목적으로 사용
Record 를 사용하면 명시적인 생성자, equals(), hashCode(), toString() 메서드를 자동으로 생성해주어 코드의 가독성을 향상
*
* */
public record SqcVo(String brand, String name, List<String> tags, String sex, String price, String view, String buy,
                    String review, String url) { }
