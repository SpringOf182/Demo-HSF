package com.demo.dao;

import com.demo.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Helen
 */
public interface BookDAO {
    /**获取当前全部书籍列表
     * @return List<Book>
     */
    List<Book> getAll();
    /**获取当前全部书籍列表
     * @param  category 类别代码
     * @return List<Book>
     */
    List<Book> getBookListByCategory(Integer category);
    /**获取当前全部书籍列表
     * @param book 增添的book对象
     * @return Boolean
     */
    Boolean addBook(Book book);
    /**获取当前全部书籍列表
     * @param bookId 要删除的书id
     * @return Boolean
     */
    Boolean deleteBook(Integer bookId);
    /**获取当前全部书籍列表
     * @param bookId 要更新的书id
     * @param remainNum 要更新的书数量
     * @return Boolean
     */
    Boolean changeRemainNum(@Param("bookID") Integer bookId, @Param("remainNum") Integer remainNum);
}