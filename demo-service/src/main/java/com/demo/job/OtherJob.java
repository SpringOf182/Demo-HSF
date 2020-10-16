package com.demo.job;

import com.google.common.collect.Lists;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Helen
 */
@Component(value = "OtherJob")
public class OtherJob {

    public void execute() throws JobExecutionException  {
        System.out.println(">>>>>>>>>>OTHER");
        List<String> bookLists = Lists.newArrayList();
        bookLists.add("test");
    }
}
