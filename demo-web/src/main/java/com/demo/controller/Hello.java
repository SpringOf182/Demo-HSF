package com.demo.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ying
 */
@RestController
public class Hello {

    @RequestMapping("/repeatTestEw.action")
    public String getAllBook(){
        return "ddd";
    }
}
