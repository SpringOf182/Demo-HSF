package com.demo.util;

import com.google.common.base.Preconditions;
import com.sun.deploy.net.URLEncoder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * @author Helen
 */
public abstract class ExcelUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 生成文件名
     */
    private static String generateFileName(String fileName) throws UnsupportedEncodingException {

        // 为文件名添加时间信息
        fileName = fileName + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + ".xls";

        // 编码字符串，使之支持中文
        return URLEncoder.encode(fileName, "utf-8");
    }

    /**
     * 导出文件——当没有ExcelModel时调用，生成ExcelModel并调用对应导出函数
     */
    public static void export( SheetModel sheetModel, String directory,String fileName) throws IOException {

        Preconditions.checkNotNull(sheetModel, "sheetModel cannot be empty");
        Preconditions.checkNotNull(fileName, "fileName cannot be null");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(sheetModel.getHeaders()), "sheet header cannot be empty");

        ExcelModel excelModel = ExcelModel.builder()
                .sheets(Collections.singletonList(sheetModel))
                .fileName(fileName)
                .build();

        export(directory, excelModel);
    }

    /**
     * 导出文件
     */
    public static void export(String directory,ExcelModel excel) throws IOException {

        Preconditions.checkNotNull(excel, "excel cannot be empty");
        Preconditions.checkNotNull(excel.getFileName(), "fileName cannot be null");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(excel.getSheets()), "sheets cannot be empty");
        String fileName = excel.getFileName();
        // 创建Excel文件对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 表头样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        HSSFFont headerFont = workbook.createFont();
        headerStyle.setFont(headerFont);
        List<SheetModel> sheets = excel.getSheets();

        for (SheetModel sheetModel : sheets) {
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(sheetModel.getHeaders()), "sheet header cannot be empty");
            HSSFSheet sheet = workbook.createSheet(sheetModel.getSheetName());
            int index = 0;
            // 填充表头
            fillRowAndStyle(sheet.createRow(index++), sheetModel.getHeaders(), headerStyle);
            // 填充表格数据
            for (List<String> row : sheetModel.getRows()) {
                if (row.size() != sheetModel.getHeaders().size()) {
                    LOG.error("====> 表头的字段数与行数据字段数和不匹配");
                }
                fillRow(sheet.createRow(index++), row);
            }
        }

        //在本地生成
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(directory + fileName);
            fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 填充数据
     */
    private static void fillRow(HSSFRow row, List<String> fields) {
        for (int i = 0; i < fields.size(); i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(fields.get(i));
            cell.setCellValue(text);
        }
    }

    /**
     * 填充数据并加样式 (一行的所有cell都用同一样式)
     */
    private static void fillRowAndStyle(HSSFRow row, List<String> fields, HSSFCellStyle cellStyle) {
        for (int i = 0; i < fields.size(); i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(fields.get(i));
            cell.setCellValue(text);
            cell.setCellStyle(cellStyle);
        }
    }
}
