package cn.isaxon.mddoc.util.helper;

import lombok.Getter;

/**
 * <p></p>
 * Created at 9/3/2025 22:41
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
public class ArabicNumberConverter implements NumberConverter {

    @Getter
    private static final ArabicNumberConverter instance = new ArabicNumberConverter();


    @Override
    public String fromArabic(int number) {
        return String.valueOf(number);
    }

    @Override
    public NumberConverterEnum getConverterEnum() {
        return NumberConverterEnum.ARABIC;
    }

}
