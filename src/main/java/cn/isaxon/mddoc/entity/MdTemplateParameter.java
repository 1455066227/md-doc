package cn.isaxon.mddoc.entity;

import cn.isaxon.mddoc.core.MdTool;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>Template parameters</p>
 * Created at 9/3/2025 20:20
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@Data
@Builder
public class MdTemplateParameter {

    private String title;

    private MdTool _tool;

    private List<MdPair<String, String>> reqArgTabHeaders;

    private List<TagItem> tagItems;

    /**
     * 给ftl调用以配置请求参数展示表头
     *
     * @param key   表头对应的key
     * @param value 表头展示的值
     * @return bool
     */
    @SuppressWarnings("unused")
    public boolean addReqTabHeader(String key, String value) {
        return reqArgTabHeaders.add(new MdPair<>(key, value));
    }

    @Builder
    @Data
    public static class TagItem {

        private String index;

        private String description;

        private List<ApiItem> apiItems;
    }

    @Builder
    @Data
    public static class ApiItem {

        /**
         * 接口对应的序列(已按照配置转化成对应的字符串结果)
         */
        private String index;

        /**
         * 接口描述
         */
        private String description;

        /**
         * 接口支持的http方法列表
         */
        private List<String> apiMethods;

        /**
         * 接口对应的http url路径列表
         */
        private List<String> apiUrls;

        /**
         * 请求参数schema列表
         */
        private List<Field> schemaFields;
    }
}
