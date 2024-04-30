package com.example.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yubin
 * @date 2024/4/28
 */
@RestController
public class TestController {
    @GetMapping("/")
    public ResponseEntity<String> heath() {
        // 可以在这里添加一些逻辑

        // 返回一个带有 HTTP 200 状态码的响应
        return ResponseEntity.ok().body("This is an empty endpoint with HTTP 200 response");
    }

}
