package com.tuling.mongodbdemo.demo;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author Fox
 */
public class MongodbDemo {


    public static void main(String[] args) {

        //连接单点
        //MongoClient mongoClient = MongoClients.create("mongodb://192.168.65.174:27017");
        //连接副本集
        MongoClient mongoClient = MongoClients.create("mongodb://fox:fox@192.168.65.174:28017,192.168.65.174:28018,192.168.65.174:28019/test?authSource=admin&replicaSet=rs0");
        //连接分片集群  节点：mongos
        //MongoClient mongoClient = MongoClients.create("mongodb://192.168.65.174:27017,192.168.65.192:27017,192.168.65.204:27017");

        //获得数据库对象
        MongoDatabase database = mongoClient.getDatabase("test");
        //获得集合
        MongoCollection<Document> collection = database.getCollection("emp");

        System.out.println("emp文档数："+collection.countDocuments());

    }
}