package cn.isaxon.mddoc.core;

import cn.isaxon.mddoc.entity.MdPair;
import cn.isaxon.mddoc.util.MarkdownUtil;
import cn.isaxon.mddoc.util.MdReflectionUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>提供ftl的工具类</p>
 * Created at 2025/3/10 21:13
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@SuppressWarnings("unused")
public class MdTool {

    /**
     * 生成对应的markdown表行列表
     *
     * @param headerPairs  表头
     * @param schemaFields 对应的有Schema的字段列表
     * @return markdown表行列表
     */
    public List<String> generateMdTable(List<MdPair<String, String>> headerPairs, List<Field> schemaFields) {
        List<Map<String, Object>> schemaMapList = schemaFields.stream()
                .map(field -> {
                    Schema schema = field.getAnnotation(Schema.class);
                    if (schema == null) {
                        return null;
                    }
                    Map<String, Object> result = MdReflectionUtil.annotationToMap(schema);
                    Object o = result.get("name");
                    if (StringUtils.isBlank(o == null ? "" : o.toString())) {
                        result.put("name", field.getName());
                    }
                    return result;
                })
                .filter(Objects::nonNull)
                .toList();

        List<String> headerKeys = headerPairs.stream().map(MdPair::getK).toList();
        List<List<String>> rowCellsList = schemaMapList.stream()
                .map(map -> headerKeys.stream().map(k -> {
                    Object o = map.get(k);
                    if (o == null) {
                        return "unknown key: " + k;
                    }
                    return o.toString();
                }).toList())
                .toList();

        List<String> headerValues = headerPairs.stream().map(MdPair::getV).toList();

        return MarkdownUtil.generateTable(headerValues, rowCellsList);
    }

}
