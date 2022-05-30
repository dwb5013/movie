package com.dingweibing.interview.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

@Document(indexName = "movieIndex")
public class Movie {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "title")
    private String title;


    @Field(type = FieldType.Date, name = "year")
    private LocalDate year;

    @Field(type = FieldType.Nested, includeInParent = true, name = "cast")
    private List<Cast> cast;

    @Field(type = FieldType.Text, name = "genres")
    private List<String> genres;
}
