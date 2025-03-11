package cn.isaxon.mddoc.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p></p>
 * Created at 9/3/2025 21:09
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@Data
@Schema(description = "用户实体")
public class User {

    @Schema(description = "用户名", example = "saxon", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @Schema(description = "密码", example = "123456")
    private String password;

    @Schema(description = "性别", example = "male")
    private String gender;

    @Schema(description = "生日", example = "1994-01-01")
    private String birthday;
}
