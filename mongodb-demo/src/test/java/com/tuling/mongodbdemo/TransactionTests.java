package com.tuling.mongodbdemo;



import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import com.tuling.mongodbdemo.service.EmployeeService;

/**
 * @author Fox
 */
public class TransactionTests extends MongodbDemoApplicationTests{

    @Autowired
    EmployeeService employeeService;



    @Test
    public void test(){
        employeeService.addEmployee();
    }

    /**
     * 事务操作API
     * https://docs.mongodb.com/upcoming/core/transactions/
     */
    @Test
    public void updateEmployeeInfo() {
        //连接复制集
        MongoClient client = MongoClients.create("mongodb://fox:fox@192.168.65.174:28017,192.168.65.174:28018,192.168.65.174:28019/test?authSource=admin&replicaSet=rs0");

        MongoCollection<Document> emp = client.getDatabase("test").getCollection("emp");
        MongoCollection<Document> events = client.getDatabase("test").getCollection("events");
        //事务操作配置
        TransactionOptions txnOptions = TransactionOptions.builder()
                .readPreference(ReadPreference.primary())
                .readConcern(ReadConcern.MAJORITY)
                .writeConcern(WriteConcern.MAJORITY)
                .build();
        try (ClientSession clientSession = client.startSession()) {
            //开启事务
            clientSession.startTransaction(txnOptions);

            try {

                emp.updateOne(clientSession,
                        Filters.eq("username", "张三"),
                        Updates.set("status", "inactive"));

                //int i=1/0;

                events.insertOne(clientSession,
                        new Document("username", "张三").append("status", new Document("new", "inactive").append("old", "Active")));

                //提交事务
                clientSession.commitTransaction();

            }catch (Exception e){
                e.printStackTrace();
                //回滚事务
                clientSession.abortTransaction();
            }
        }
    }






}
