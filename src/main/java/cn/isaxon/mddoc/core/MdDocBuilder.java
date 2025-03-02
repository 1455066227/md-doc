package cn.isaxon.mddoc.core;

import cn.isaxon.mddoc.config.MdDocConfig;
import cn.isaxon.mddoc.util.MdReflectionUtil;
import cn.isaxon.mddoc.util.NumberConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p></p>
 * Created at 28/2/2025 20:07
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@Component
@AllArgsConstructor
public class MdDocBuilder {
    public static final String CRLF = "\r\n";
    public static final String CR = "\r";
    public static final String LF = "\n";

    private final MdDocConfig mdDocConfig;

    @PostConstruct
    public void generate() {
        Set<Class<?>> tagClasses = MdReflectionUtil.scanTypesAnnotatedWith(mdDocConfig.getBasePackage(), Tag.class);
        AtomicInteger tagAtomicIndex = new AtomicInteger(0);
        List<String> list = tagClasses.stream()
                .map(clazz -> {
                    RequestMapping tagRequestMapping = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
                    if (tagRequestMapping == null) {
                        return null;
                    }
                    StringBuilder result = new StringBuilder();
                    int tagIndex = tagAtomicIndex.incrementAndGet();
                    result.append("## ").append(NumberConverter.toChineseNumber(tagIndex)).append(". ").append(clazz.getAnnotation(Tag.class).description()).append(CRLF);
                    AtomicInteger apiAtomicIndex = new AtomicInteger(0);
                    List<String> apiList = Arrays.stream(clazz.getMethods())
                            .map(method -> {
                                RequestMapping apiRequestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                                Operation operation = AnnotationUtils.findAnnotation(method, Operation.class);
                                if (operation != null && apiRequestMapping != null) {
                                    int apiIndex = apiAtomicIndex.incrementAndGet();
                                    StringBuilder apiSb = new StringBuilder();
                                    List<String> requestMethodNames = Arrays.stream(apiRequestMapping.method()).map(Enum::toString).toList();
                                    String[] firstPaths = (String[]) AnnotationUtils.getValue(tagRequestMapping);
                                    String[] secondPaths = (String[]) AnnotationUtils.getValue(apiRequestMapping);

                                    if (firstPaths != null && firstPaths.length > 0) {
                                        List<String> pathList = Arrays.stream(firstPaths).flatMap(s -> {
                                            if (secondPaths != null && secondPaths.length > 0) {
                                                return Arrays.stream(secondPaths).map(s1 -> s + "/" + s1);
                                            }
                                            return Stream.of(s);
                                        }).toList();
                                        return apiSb.append("### ").append(apiIndex).append(". ").append(operation.description()).append(CRLF)
                                                .append("  1. ").append(String.join("/", requestMethodNames)).append(" ").append("[").append(String.join(", ", pathList)).append("]").append(CRLF)
//                                                .append("  2. ").append(httpMethodName.toUpperCase()).append(" ").append(paths.length == 1 ? paths[0] : String.join(",", paths)).append(CRLF)
                                                .toString();
                                    }
                                }
                                return null;
                            })
                            .filter(Objects::nonNull)
                            .toList();
                    apiList.forEach(apiSb -> result.append(apiSb).append(CRLF));
                    return result.toString();
                })
                .filter(Objects::nonNull)
                .toList();
        list.forEach(System.out::println);
    }
}
