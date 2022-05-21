package com.tuling.mongodbdemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
public class MongodbDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(MongodbDemo2Application.class, args);
    }

}
