package cn.isaxon.mddoc.util;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MarkdownUtilTest {


    @Test
    public void generateTable() throws Exception {
        ArrayList<List<String>> rowCellsList = new ArrayList<>();
        rowCellsList.add(Arrays.asList("age", "int", "是"));
        rowCellsList.add(Arrays.asList("name", "string", "否"));
        rowCellsList.add(Arrays.asList("address", "string", "否"));
        System.out.println(MarkdownUtil.generateTableStr(Arrays.asList("参数", "类型", "是否必填"), rowCellsList));
    }
}