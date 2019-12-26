package com.java.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    @Id
    private String skuId;
    private String name;
    private String address;
    private Integer age;
    private String sex;
    private String city;

}
