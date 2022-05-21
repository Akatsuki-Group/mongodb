package com.tuling.mongodbdemo2.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tuling.mongodbdemo2.entity.Book;

/**
 * @author Fox
 * 使用QBE（Query By Example）
 */
public interface BookRepository extends MongoRepository<Book,String> {

    //等价于 db.book.findOne({title:"xxx"})
    Book findOneByTitle(String title);

    //等价于 db.book.findOne({author:"xxx"},{_id:0,title:1,voteCount:1,publishDate:1})
    @Query(fields = "{_id:0,title:1,voteCount:1,publishDate:1}")
    List<Book> findByAuthor(String author);


    //等价于  db.book.find({category:"xxx"}).sort({publishDate:-1})
    List<Book> findByCategoryOrderByPublishDateDesc(String category, Pageable pageable);

}
