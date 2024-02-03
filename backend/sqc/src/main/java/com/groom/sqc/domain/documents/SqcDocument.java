package com.groom.sqc.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

//실제 데이터베이스 : product / 테스트 : shoes

@Document(collection = "product")
@AllArgsConstructor
@Getter
@RequiredArgsConstructor
public class SqcDocument<T> {

    @Id
    private String id;
    private String brand;
    private String name;
    private List<String> tag;
    private String sex;
    private Integer price;
    private List<T> size;
    private String view;
    private String buy;
    private String like;
    private String review;
    private String url;
    private String img;
}
