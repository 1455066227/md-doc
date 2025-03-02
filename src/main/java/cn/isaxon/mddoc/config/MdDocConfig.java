package cn.isaxon.mddoc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 * Created at 28/2/2025 20:09
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@ConfigurationProperties(prefix = "md-doc")
@Component
@Data
public class MdDocConfig {

    private String basePackage;

}
