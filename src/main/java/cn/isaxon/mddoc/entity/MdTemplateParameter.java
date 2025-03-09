package cn.isaxon.mddoc.entity;

import lombok.Builder;
import lombok.Data;

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

    private List<TagItem> tagItems;

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

        private String index;

        private String description;

        private List<String> apiMethods;

        private List<String> apiUrls;
    }
}
