package cn.isaxon.mddoc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 * Created at 27/2/2025 21:27
 *
 * @author Saxon Lai
 * Copyright © 2025 Saxon Lai(分段的函数) | <a href="https://isaxon.cn">1455066227@qq.com</a>
 */
@RequestMapping("/test")
@RestController
@Tag(name = "test", description = "测试接口")
public class TestController {

    @Operation(description = "你好")
    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }

}
