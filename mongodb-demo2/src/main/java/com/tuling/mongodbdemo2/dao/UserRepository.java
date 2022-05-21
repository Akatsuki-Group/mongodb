package com.tuling.mongodbdemo2.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuling.mongodbdemo2.entity.User;

/**
 * @author Fox
 */
public interface UserRepository extends MongoRepository<User,String> {
}
