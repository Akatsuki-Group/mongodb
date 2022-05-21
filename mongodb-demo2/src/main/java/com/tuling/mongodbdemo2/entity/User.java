package com.tuling.mongodbdemo2.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

/**
 * @author Fox
 */
@Document("user")
@Data
@Builder
public class User {

    @Id
    private String id;

    //用户信息key：value方式存储
    private UserAttrs userAttrs;
}
