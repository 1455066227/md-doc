package cn.isaxon.mddoc.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberConverterTest {

    @Test
    void toChineseNumber() {
        for (int i = 0; i < 16; i++) {
            System.out.println(NumberConverter.toChineseNumber(i));
        }
        System.out.println(NumberConverter.toChineseNumber(110));
    }

    @Test
    void toRomanNumber() {
        for (int i = 0; i < 16; i++) {
            System.out.println(NumberConverter.toRomanNumber(i));
        }
        System.out.println(NumberConverter.toRomanNumber(110));
    }
}