package cn.isaxon.mddoc.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>各版本数字转换</p>
 * Created at 2/3/2025 23:10
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
public class NumberConverter {

    // 中文数字和单位
    private static final String[] CHINESE_NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] UNITS = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿"};

    // 罗马数字和对应的值
    private static final Map<Character, Integer> ROMAN_VALUES = new HashMap<>();
    private static final int[] ROMAN_VALUES_ARRAY = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    static {
        ROMAN_VALUES.put('I', 1);
        ROMAN_VALUES.put('V', 5);
        ROMAN_VALUES.put('X', 10);
        ROMAN_VALUES.put('L', 50);
        ROMAN_VALUES.put('C', 100);
        ROMAN_VALUES.put('D', 500);
        ROMAN_VALUES.put('M', 1000);
    }

    // 阿拉伯数字转中文数字
    public static String toChineseNumber(int number) {
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


    // 阿拉伯数字转罗马数字
    public static String toRomanNumber(int number) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ROMAN_VALUES_ARRAY.length; i++) {
            while (number >= ROMAN_VALUES_ARRAY[i]) {
                number -= ROMAN_VALUES_ARRAY[i];
                result.append(ROMAN_SYMBOLS[i]);
            }
        }
        return result.toString();
    }

    // 罗马数字转阿拉伯数字
    public static int romanNumberToArabicNumber(String romanNumber) {
        int result = 0;
        int prevValue = 0;

        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            int currentValue = ROMAN_VALUES.get(romanNumber.charAt(i));
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }

        return result;
    }
}