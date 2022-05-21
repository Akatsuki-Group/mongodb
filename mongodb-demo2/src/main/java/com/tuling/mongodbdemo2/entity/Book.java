package com.tuling.mongodbdemo2.entity;


import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

/**
 * @author Fox
 */

@Data
@Builder
@Document("book")
// ＠CompoundIndexes，表示集合上的复合索引集合
// ＠CompoundIndex，表示一个复合索引，name是索引名称，而def则是索引的字段定义
@CompoundIndexes({
        @CompoundIndex(name="idx_category_publishDate",def = "{'category':1,'publishDate':1}")
})
public class Book {
    @Id
    private String id;

    @Indexed  //单键索引
    private String author;

    private String category;  //书籍分类

    @Indexed(unique = true)   //单键索引，其中unique=true表示唯一索引。
    private String title;  //书籍标题

    private Integer voteCount; //投票数量

    private DateTime publishDate; //发布日期

}
