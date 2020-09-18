package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private Integer bookID;

    //@NotNull(message = "书名不能为空")
    private String bookName;

    //@NotNull(message = "类别代码不能为空")
    private Integer category;

    //@NotNull(message = "数量不能为空")
    private Integer remainNum;


    public Book() {

    }
}
