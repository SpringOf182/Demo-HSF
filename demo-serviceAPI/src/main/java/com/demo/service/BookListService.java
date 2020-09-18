package com.demo.service;

import com.demo.pojo.Book;

import java.util.List;

public interface BookListService {
    List<Book> getAll();

    List<Book> getBookListByCategory(Integer category);

    Boolean addBook(Book book);

    Boolean deleteBook(Integer bookId);

    Boolean changeRemainNum(Integer bookID, Integer newNumber);
}
