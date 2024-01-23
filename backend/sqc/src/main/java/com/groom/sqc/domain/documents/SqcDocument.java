package com.groom.sqc.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "sqcCollection")
@AllArgsConstructor
@Getter
@RequiredArgsConstructor
public class SqcDocument {

    @Id
    private Long id;

    private String brand;
    private String name;
    private List<String> tags;
    private String sex;
    private String price;
    private String view;
    private String buy;
    private String review;
    private String url;

}
