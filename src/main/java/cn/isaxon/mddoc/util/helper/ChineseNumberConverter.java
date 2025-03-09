package cn.isaxon.mddoc.util.helper;

import lombok.Getter;

/**
 * <p></p>
 * Created at 9/3/2025 22:21
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
public class ChineseNumberConverter implements NumberConverter {

    @Getter
    private static final ChineseNumberConverter instance = new ChineseNumberConverter();

    private ChineseNumberConverter() {
    }

    /**
     * 中文数字和单位
     */
    private static final String[] CHINESE_NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] UNITS = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿"};


    @Override
    public String fromArabic(int number) {
        if (number == 0) {
            return CHINESE_NUMBERS[0];
        }

        StringBuilder resultSb = new StringBuilder();
        int unitIndex = 0;

        while (number > 0) {
            int digit = number % 10;
            if (digit != 0) {
                resultSb.insert(0, UNITS[unitIndex]);
                resultSb.insert(0, CHINESE_NUMBERS[digit]);
            } else if (!resultSb.toString().startsWith(CHINESE_NUMBERS[0])) {
                resultSb.insert(0, CHINESE_NUMBERS[0]);
            }
            number /= 10;
            unitIndex++;
        }

        // 处理特殊情况，如“一十”应为“十”
        String result = resultSb.toString();
        if (result.startsWith("一十")) {
            result = result.replaceFirst("一十", "十");
        }
        if (result.endsWith("零")) {
            result = "零".equals(result) ? "零" : result.substring(0, result.length() - 1);
        }

        return result;
    }

    @Override
    public NumberConverterEnum getConverterEnum() {
        return NumberConverterEnum.CHINESE;
    }
}
