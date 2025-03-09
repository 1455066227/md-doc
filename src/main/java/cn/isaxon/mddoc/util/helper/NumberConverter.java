package cn.isaxon.mddoc.util.helper;

import java.util.List;

/**
 * <p>各版本数字转换</p>
 * Created at 2/3/2025 23:10
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
public interface NumberConverter {

    List<NumberConverter> allNumberConverters = List.of(
            ChineseNumberConverter.getInstance(),
            RomanNumberConverter.getInstance(),
            ArabicNumberConverter.getInstance()
    );

    enum NumberConverterEnum {
        CHINESE, ARABIC, ROMAN
    }

    static String fromArabic(int number, NumberConverterEnum converterEnum) {
        return allNumberConverters.stream()
                .filter(numberConverter -> numberConverter.getConverterEnum() == converterEnum)
                .findFirst()
                .orElse(ChineseNumberConverter.getInstance())
                .fromArabic(number);
    }

    String fromArabic(int number);

    NumberConverterEnum getConverterEnum();
}