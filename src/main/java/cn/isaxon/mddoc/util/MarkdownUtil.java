package cn.isaxon.mddoc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Markdown util</p>
 * Created at 27/2/2025 20:22
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
public class MarkdownUtil {

    public static String generateTableStr(List<String> headers, List<List<String>> rowCellsList) {
        return String.join("\r\n", generateTable(headers, rowCellsList));
    }

    public static List<String> generateTable(List<String> headers, List<List<String>> rowCellsList) {
        // compute max length of each column
        Map<Integer, Integer> column2MaxLengthMap = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            column2MaxLengthMap.compute(i, (k, v) -> v == null ? header.length() : Math.max(v, header.length()));
        }
        for (List<String> rowCells : rowCellsList) {
            for (int i = 0; i < rowCells.size(); i++) {
                String rowCell = rowCells.get(i);
                column2MaxLengthMap.compute(i, (k, v) -> v == null ? rowCell.length() : Math.max(v, rowCell.length()));
            }
        }

        // generate table
        List<String> result = new ArrayList<>();
        StringBuilder headerLineSb = new StringBuilder(), separatorLineSb = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            int maxLength = column2MaxLengthMap.get(i);
            headerLineSb.append("| ")
                    .append(String.format("%-" + maxLength + "s", header))
                    .append(" ");
            separatorLineSb.append("| ")
                    .append("-".repeat(maxLength))
                    .append(" ");
            if (i == headers.size() - 1) {
                headerLineSb.append("|");
                separatorLineSb.append("|");
            }
        }
        result.add(headerLineSb.toString());
        result.add(separatorLineSb.toString());

        for (List<String> rowCells : rowCellsList) {
            StringBuilder lineSb = new StringBuilder();
            for (int i = 0; i < rowCells.size(); i++) {
                String rowCell = rowCells.get(i);
                int maxLength = column2MaxLengthMap.get(i);
                lineSb.append("| ")
                        .append(String.format("%-" + maxLength + "s", rowCell))
                        .append(" ");
                if (i == rowCells.size() - 1) {
                    lineSb.append("|");
                }
            }
            result.add(lineSb.toString());
        }
        return result;
    }
}
