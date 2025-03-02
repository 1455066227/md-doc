package cn.isaxon.mddoc.util;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * <p></p>
 * Created at 28/2/2025 20:13
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
public class MdReflectionUtil {

    public static Set<Class<?>> scanTypesAnnotatedWith(String basePackage, Class<? extends Annotation> clazz) {
        Reflections reflections = new Reflections(basePackage);
        return reflections.getTypesAnnotatedWith(clazz);
    }
}
