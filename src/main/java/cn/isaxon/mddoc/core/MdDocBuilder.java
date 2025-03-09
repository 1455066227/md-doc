package cn.isaxon.mddoc.core;

import cn.isaxon.mddoc.config.MdDocConfig;
import cn.isaxon.mddoc.entity.MdTemplateParameter;
import cn.isaxon.mddoc.util.MdReflectionUtil;
import cn.isaxon.mddoc.util.helper.NumberConverter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * <p></p>
 * Created at 28/2/2025 20:07
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@Component
@Slf4j
public class MdDocBuilder {

    private final MdDocConfig mdDocConfig;
    private final Configuration freeMarkerCfg;
    private String templateCode;

    public MdDocBuilder(MdDocConfig mdDocConfig) {
        this.mdDocConfig = mdDocConfig;
        this.freeMarkerCfg = new Configuration(Configuration.VERSION_2_3_31);
        this.freeMarkerCfg.setClassForTemplateLoading(MdDocBuilder.class, "/");
        try {
            File file = ResourceUtils.getFile("classpath:template.ftl");
            templateCode = FileUtils.readFileToString(file);
        } catch (Exception e) {
            log.error(e.getMessage());
            templateCode = null;
        }
    }

    @PostConstruct
    public void generate() {
        Set<Class<?>> tagClasses = MdReflectionUtil.scanTypesAnnotatedWith(mdDocConfig.getBasePackage(), Tag.class);
        AtomicInteger tagAtomicIndex = new AtomicInteger(0);

        List<MdTemplateParameter.TagItem> tagItems = tagClasses.stream()
                .map(clazz -> {
                    RequestMapping tagRequestMapping = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
                    if (tagRequestMapping == null) {
                        return null;
                    }
                    int tagIndex = tagAtomicIndex.incrementAndGet();
                    AtomicInteger apiAtomicIndex = new AtomicInteger(0);
                    List<MdTemplateParameter.ApiItem> apiList = Arrays.stream(clazz.getMethods())
                            .map(method -> {
                                Optional<Annotation> metaApiRequestMappingOptional = MdReflectionUtil.getMetaAnnotationAnnotatedWith(method, RequestMapping.class);
                                RequestMapping apiRequestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                                Operation operation = AnnotationUtils.findAnnotation(method, Operation.class);
                                if (operation != null && metaApiRequestMappingOptional.isPresent() && apiRequestMapping != null) {
                                    int apiIndex = apiAtomicIndex.incrementAndGet();
                                    Annotation metaApiRequestMapping = metaApiRequestMappingOptional.get();
                                    List<String> requestMethodNames = Arrays.stream(apiRequestMapping.method()).map(Enum::toString).toList();
                                    String[] firstPaths = (String[]) AnnotationUtils.getValue(tagRequestMapping);
                                    String[] secondPaths = (String[]) AnnotationUtils.getValue(metaApiRequestMapping);

                                    if (firstPaths != null && firstPaths.length > 0) {
                                        List<String> pathList = Arrays.stream(firstPaths).flatMap(s -> {
                                            if (secondPaths != null && secondPaths.length > 0) {
                                                return Arrays.stream(secondPaths).map(s1 -> (s + "/" + s1).replaceAll("/+", "/"));
                                            }
                                            return Stream.of(s.replaceAll("/+", "/"));
                                        }).toList();

                                        return MdTemplateParameter.ApiItem.builder()
                                                .index(NumberConverter.fromArabic(apiIndex, mdDocConfig.getApiIndexNumber()))
                                                .description(operation.description())
                                                .apiMethods(requestMethodNames)
                                                .apiUrls(pathList)
                                                .build();
                                    }
                                }
                                return null;
                            })
                            .filter(Objects::nonNull)
                            .toList();
                    return MdTemplateParameter.TagItem.builder()
                            .index(NumberConverter.fromArabic(tagIndex, mdDocConfig.getTagIndexNumber()))
                            .description(clazz.getAnnotation(Tag.class).description())
                            .apiItems(apiList)
                            .build();
                })
                .filter(Objects::nonNull)
                .toList();

        MdTemplateParameter templateParameter = MdTemplateParameter.builder()
                .title("Markdown API")
                .tagItems(tagItems)
                .build();
        String docContent = generateMdDocByFreeMarker(templateParameter);
        System.out.println(docContent);
    }

    public String generateMdDocByFreeMarker(MdTemplateParameter mdTemplateParameter) {
        try {
            StringWriter out = new StringWriter();
            new Template("inline", templateCode, freeMarkerCfg).process(mdTemplateParameter, out);
            return out.toString();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }
}
