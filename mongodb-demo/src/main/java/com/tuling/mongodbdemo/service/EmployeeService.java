package com.tuling.mongodbdemo.service;

import java.util.Date;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuling.mongodbdemo.entity.Employee;

/**
 * @author Fox
 */
@Service
public class EmployeeService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Transactional
    public void addEmployee(){
        Employee employee = new Employee(100,"张三", 21,
                10000.00, new Date());
        Employee employee2 = new Employee(101,"赵六", 28,
                10000.00, new Date());

        mongoTemplate.save(employee);
        int i=1/0;
        mongoTemplate.save(employee2);

    }
}
