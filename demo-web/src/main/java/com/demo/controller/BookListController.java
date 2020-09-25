package com.demo.controller;

import com.demo.pojo.Book;
import com.demo.service.BookListService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ying
 */
@RestController
public class BookListController {

    @Autowired
    @Setter
    BookListService bookListService ;

    @RequestMapping(value = "/getAll.action",method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAllBook(){
        List<Book> test = new ArrayList<Book>();
        test = bookListService.getAll();
        System.out.println(test);
        return test;
    }
    /*public String getAllBook(Model model){
        List<Book> bookList = bookListService.getAll();
        System.out.println(bookList);
        model.addAttribute("bookList", bookList);
        System.out.println(model);
        return "show.html";
    }
    public ModelAndView getAllBook(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("BookList.jsp");//参数为指定页面
        List<Book> bookList = bookListService.getAll();
        modelAndView.addObject("bookList", bookList);
        return modelAndView;
    }*/

    @RequestMapping(value = "/getBookListByCategory.action",method = RequestMethod.POST)
    @ResponseBody
    public List<Book> getBookByCategory(@RequestBody Integer category){
        return bookListService.getBookListByCategory(category);
    }
    /*public ModelAndView getBookByCategory(HttpServletRequest request){
        Integer category = Integer.valueOf(request.getParameter("category"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("BookList");//参数为指定页面
        List<Book> bookList = bookListService.getBookListByCategory(category);
        modelAndView.addObject("bookList", bookList);
        return modelAndView;
    }*/

    @RequestMapping(value = "/addBook.action",method = RequestMethod.POST)
    @ResponseBody
    public List<Book> addBook(@RequestBody Book book) {
        //@RequestBody 同时请求header应为'content-type': 'application/json',
        bookListService.addBook(book);
        return bookListService.getAll();
    }
    /*public String addBook(@ModelAttribute @Valid Book book, Model model){
        bookListService.addBook(book);
        List<Book> bookList = bookListService.getAll();
        model.addAttribute("bookList", bookList);
        return "show.html";//参数为指定页面
    }*/

    @RequestMapping(value = "/deleteBook.action",method = RequestMethod.POST)
    @ResponseBody
    public List<Book> deleteBook(@RequestBody Integer bookId){
        bookListService.deleteBook(bookId);
        return bookListService.getAll();
    }

    @RequestMapping(value = "/changeRemainNum.action",method = RequestMethod.POST)
    @ResponseBody
    public List<Book> changeRemainNum(@RequestBody Book book){
        System.out.println("++++++++++>>>>>>>");
        System.out.println("++++++++++>>>>>>>"+book);
        bookListService.changeRemainNum(Integer.valueOf(book.getBookID()),Integer.valueOf(book.getRemainNum()));
        return bookListService.getAll();
    }
}
