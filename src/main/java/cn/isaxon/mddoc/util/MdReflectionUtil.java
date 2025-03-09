package cn.isaxon.mddoc.util;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
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

    /**
     * 扫描获取<code>method</code>所有注解, 然后返回<code>annotationClass</code>注解的元注解
     *
     * @param method              方法
     * @param metaAnnotationClass 元注解类型
     * @return 元注解对象
     */
    public static Optional<Annotation> getMetaAnnotationAnnotatedWith(Method method, Class<? extends Annotation> metaAnnotationClass) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> isAnnotatedWith(annotation.annotationType(), metaAnnotationClass))
                .findFirst();
    }

    /**
     * 递归判断注解A是否被注解B注解过
     *
     * @param annotationClass       要检查的注解类（注解A）
     * @param targetAnnotationClass 目标注解类（注解B）
     * @return 如果注解A被注解B注解过，返回true；否则返回false
     */
    public static boolean isAnnotatedWith(Class<? extends Annotation> annotationClass, Class<? extends Annotation> targetAnnotationClass) {
        return isAnnotatedWith(annotationClass, targetAnnotationClass, new HashSet<>());
    }

    private static boolean isAnnotatedWith(Class<? extends Annotation> annotationClass, Class<? extends Annotation> targetAnnotationClass, Set<Class<? extends Annotation>> checkedAnnotations) {
        // 如果已经检查过这个注解，直接返回false
        if (checkedAnnotations.contains(annotationClass)) {
            return false;
        }
        // 将当前注解加入已检查集合
        checkedAnnotations.add(annotationClass);

        // 获取注解A的所有元注解
        Annotation[] annotations = annotationClass.getAnnotations();
        for (Annotation annotation : annotations) {
            // 如果当前元注解就是注解B，直接返回true
            if (annotation.annotationType() == targetAnnotationClass) {
                return true;
            }
            // 递归检查当前元注解是否被注解B注解过
            if (isAnnotatedWith(annotation.annotationType(), targetAnnotationClass, checkedAnnotations)) {
                return true;
            }
        }
        // 如果没有找到注解B，返回false
        return false;
    }

}
