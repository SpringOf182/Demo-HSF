package com.demo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 应该移到serviceAPI中
 *
 * @author Helen
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelModel {
    @Builder.Default
    private String fileName = "数据导出";

    @Builder.Default
    private List<SheetModel> sheets = Collections.emptyList();
}