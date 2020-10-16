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
public class SheetModel {
    @Builder.Default
    private String sheetName = "sheet1";

    @Builder.Default
    private List<String> headers = Collections.emptyList();

    @Builder.Default
    private List<List<String>> rows = Collections.emptyList();
}
