package cn.isaxon.mddoc.entity;

import cn.isaxon.mddoc.util.JsonUtil;
import cn.isaxon.mddoc.util.MdReflectionUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Created at 2025/3/10 22:25
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 * @see Schema
 */
@Getter
@Setter
public class MdSchema {

    public static MdSchema ofSchema(Schema schema) {
        Map<String, Object> stringObjectMap = MdReflectionUtil.annotationToMap(schema);
        return JsonUtil.fromJson(JsonUtil.toJson(stringObjectMap), MdSchema.class);
    }

    private String name;

    private String title;

    private boolean required;

    private String description;

    private double multipleOf;

    private String maximum;

    private boolean exclusiveMaximum;
    private String minimum;
    boolean exclusiveMinimum;
    private int maxLength;
    private int minLength;
    private String pattern;
    private int maxProperties;
    private int minProperties;
    private String[] requiredProperties;
    private String format;
    private String ref;
    private boolean nullable;
    private boolean readOnly;
    private boolean writeOnly;
    private String example;
    private boolean deprecated;
    private String type;
    private String[] allowableValues;
    private String defaultValue;
    private String discriminatorProperty;
    private boolean hidden;
    private boolean enumAsRef;
    private String[] types;
    private int exclusiveMaximumValue;
    private int exclusiveMinimumValue;
    private String $id;
    private String $schema;
    private String $anchor;
    private String $vocabulary;
    private String $dynamicAnchor;
    private String contentEncoding;
    private String contentMediaType;
    private int maxContains;
    private int minContains;
    private String $comment;
    private String[] examples;
    private String _const;
}
