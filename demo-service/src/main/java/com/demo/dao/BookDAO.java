package com.demo.dao;

import com.demo.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDAO {
    public List<Book> getAll();
    public List<Book> getBookListByCategory(Integer category);
    public Boolean addBook(Book book);
    public Boolean deleteBook(Integer bookId);
    public Boolean changeRemainNum(@Param("bookID") Integer bookID, @Param("remainNum") Integer remainNum);
}