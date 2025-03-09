package cn.isaxon.mddoc.util;

import cn.isaxon.mddoc.util.helper.ChineseNumberConverter;
import cn.isaxon.mddoc.util.helper.NumberConverter;
import cn.isaxon.mddoc.util.helper.RomanNumberConverter;
import org.junit.jupiter.api.Test;

class NumberConverterTest {

    @Test
    void toChineseNumber() {
        for (int i = 0; i < 16; i++) {
            System.out.println(ChineseNumberConverter.getInstance().fromArabic(i));
        }
        System.out.println(ChineseNumberConverter.getInstance().fromArabic(110));
    }

    @Test
    void toRomanNumber() {
        for (int i = 0; i < 16; i++) {
            System.out.println(RomanNumberConverter.getInstance().fromArabic(i));
        }
        System.out.println(RomanNumberConverter.getInstance().fromArabic(110));
    }
}