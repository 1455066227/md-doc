package cn.isaxon.mddoc.example;

import cn.isaxon.mddoc.example.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 * Created at 27/2/2025 21:27
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@RequestMapping("/user")
@RestController
@Tag(name = "用户接口", description = "用户接口")
public class UserController {

    @Operation(description = "用户登录")
    @PostMapping("/signIn")
    public User signIn(@RequestBody User user) {
        user.setUserName("saxon");
        user.setGender("male");
        user.setBirthday("1994-01-01");
        return user;
    }

    @Operation(description = "用户注册")
    @GetMapping("/signUp")
    public int signUp(@RequestBody User user) {
        return 1;
    }
}
