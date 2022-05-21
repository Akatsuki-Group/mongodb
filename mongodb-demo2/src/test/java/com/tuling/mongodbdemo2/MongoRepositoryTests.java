package com.tuling.mongodbdemo2;


import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.tuling.mongodbdemo2.dao.BookRepository;
import com.tuling.mongodbdemo2.dao.UserRepository;
import com.tuling.mongodbdemo2.entity.Book;
import com.tuling.mongodbdemo2.entity.User;
import com.tuling.mongodbdemo2.entity.UserAttrs;

/**
 * @author Fox
 */
public class MongoRepositoryTests extends MongodbDemo2ApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testInsert(){

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime publishDate = format.parseDateTime("2002-01-01");

        //创建实体
        Book book = Book.builder()
                .author("余秋雨")
                .title("山居笔记")
                .category("散文")
                .publishDate(publishDate)
                .voteCount(100)
                .build();

        Book exist = bookRepository.findOneByTitle("山居笔记");
        if(exist !=null){
            //根据id删除
            bookRepository.deleteById(exist.getId());
        }
        //保存
        book = bookRepository.save(book);
        System.out.println(book);
    }

    @Test
    public void testFind(){

        //根据标题查询
        Book book = bookRepository.findOneByTitle("山居笔记");
        System.out.println(book);


        //分页查找
        List<Book> books = bookRepository.findByCategoryOrderByPublishDateDesc("散文", PageRequest.of(0, 100));

        //根据id删除
        //bookRepository.deleteById(book.getId());
    }


    @Test
    public void testFindProjection(){

        //投影实现
        List<Book> books = bookRepository.findByAuthor("余秋雨");

        System.out.println(books.toString());
    }

    @Test
    public void testUser(){

        UserAttrs attrs = new UserAttrs();
        attrs.put("name","Fox");
        attrs.put("age",30);

        User user = User.builder().userAttrs(attrs).build();

        userRepository.insert(user);

    }

}
