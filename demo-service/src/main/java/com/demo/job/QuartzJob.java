package com.demo.job;

import com.demo.dao.BookDAO;
import com.demo.pojo.Book;
import com.demo.util.ExcelUtils;
import com.demo.util.SftpConstants;
import com.demo.util.SftpUtil;
import com.demo.util.SheetModel;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Helen
 */
public class QuartzJob {

    @Autowired
    private BookDAO bookDAO;
    private SftpUtil sftp = new SftpUtil(
            SftpConstants.SFTP_REQ_USERNAME,
            SftpConstants.SFTP_REQ_PASSWORD,
            SftpConstants.SFTP_REQ_HOST,
            SftpConstants.SFTP_DEFAULT_PORT);

    public void execute() throws IOException, SftpException {
        System.out.println("任务执行");
        List<Book> bookLists = bookDAO.getAll();
        List<List<String>> rows = new ArrayList<>();
        for (Book book : bookLists) {
            List<String> row = new ArrayList<>();
            row.add(book.getBookID().toString());
            row.add(book.getBookName());
            row.add(book.getCategory().toString());
            row.add(book.getRemainNum().toString());
            rows.add(row);
        }
        List<String> header = new ArrayList();
        header.add("ID");
        header.add("书名");
        header.add("类别代码");
        header.add("数量");
        SheetModel sheetModel = SheetModel.builder()
                .headers(header)
                .rows(rows)
                .build();
        String directory = "D:\\Documents\\";
        String fileName = "Test.xls";
        String uploadFile = directory.concat(fileName);
        ExcelUtils.export(sheetModel, directory, fileName);
        String sftpDirectory = "/home/spring/Documents";
        sftp.login();
        sftp.upload(sftpDirectory,uploadFile);
        sftp.logout();
    }
}