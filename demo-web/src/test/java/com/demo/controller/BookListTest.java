package com.demo.controller;

import com.demo.pojo.Book;
import com.demo.service.BookListService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookListTest {

        @Autowired
        private BookListController bookListController;
        @Autowired
        private Hello hello;

        @Test
        public void testGetAll() throws Exception{
            //List<Book> list = bookListController.getAllBook();
            List<Book> list = new ArrayList<>();
            List<Book> test = new ArrayList<>();
            //list = bookListController.getAllBook();
            if(list!=null) {
                    Book book = list.get(0);
                    System.out.println("输出测试！！！" );
            }
        }
}
