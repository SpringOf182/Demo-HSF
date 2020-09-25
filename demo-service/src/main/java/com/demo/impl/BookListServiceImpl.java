package com.demo.impl;

import com.demo.dao.BookDAO;
import com.demo.pojo.Book;
import com.demo.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Helen
 */
@Service
public class BookListServiceImpl implements BookListService {

    @Autowired
    BookDAO bookDAO;

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public List<Book> getBookListByCategory(Integer category) {
        return bookDAO.getBookListByCategory(category);
    }

    @Override
    public Boolean addBook(Book book) {
        return bookDAO.addBook(book);
    }

    @Override
    public Boolean deleteBook(Integer bookId) {
        return bookDAO.deleteBook(bookId);
    }

    @Override
    public Boolean changeRemainNum(Integer bookId,Integer newNumber) {
        return bookDAO.changeRemainNum(bookId,newNumber);
    }
}
