package com.groom.sqc.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "shoes")
@AllArgsConstructor
@Getter
@RequiredArgsConstructor
public class SqcDocument {

    @Id
    private ObjectId id;
    private String brand;
    private String buy;
    private String like;
    private String name;
    private String price;
    private String review;
    private String sex;
    private List<Map<String, List<String>>> size;
    private List<String> tag;
    private String url;
    private String view;

}
