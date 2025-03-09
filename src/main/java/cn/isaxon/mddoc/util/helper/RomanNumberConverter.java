package cn.isaxon.mddoc.util.helper;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * Created at 9/3/2025 22:21
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
public class RomanNumberConverter implements NumberConverter {

    @Getter
    private static final RomanNumberConverter instance = new RomanNumberConverter();

    private RomanNumberConverter() {
    }

    /**
     * 罗马数字和对应的值
     */
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

    @Override
    public String fromArabic(int number) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ROMAN_VALUES_ARRAY.length; i++) {
            while (number >= ROMAN_VALUES_ARRAY[i]) {
                number -= ROMAN_VALUES_ARRAY[i];
                result.append(ROMAN_SYMBOLS[i]);
            }
        }
        return result.toString();
    }

    @Override
    public NumberConverterEnum getConverterEnum() {
        return NumberConverterEnum.ROMAN;
    }


    /**
     * 罗马数字转阿拉伯数字
     *
     * @param romanNumber 罗马数字
     * @return 阿拉伯数字
     */
    public static int toArabicNumber(String romanNumber) {
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
