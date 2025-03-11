package cn.isaxon.mddoc.entity;

import cn.isaxon.mddoc.util.MarkdownUtil;
import cn.isaxon.mddoc.util.MdReflectionUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p></p>
 * Created at 2025/3/10 21:27
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class MdTableData {

    public List<String> generateMdTable() {
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

    private List<Field> schemaFields;

    private List<MdPair<String, String>> headerPairs;

}
