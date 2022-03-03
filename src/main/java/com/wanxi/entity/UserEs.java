package com.wanxi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "user_es", shards = 2, replicas = 1)
public class UserEs {
    private int id;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String username;
    private String password;
    private String code;
    private String sessionCode;
    private String address;
    private String email;
    private String phone;
    private String sex;
    private String birthday;
    private String hobby;
    private String start;
    private String end;
    private String text;
    private String city;
}