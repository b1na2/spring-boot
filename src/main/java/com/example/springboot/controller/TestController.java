package com.example.springboot.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * test
 * @author yubin
 * @date 2024/4/27
 */
@RestController
public class TestController {

    private static final String INTERNAL_IP = "192.168.0.48:9376";
    private static final String DOMAIN_NAME = "c403d7504a8d42e6b9153ae11a5a689f.internal.nebulab.app:3306";

    @GetMapping("/")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok().body("This is an empty endpoint with HTTP 200 response");
    }

    @GetMapping("/test")
    public ResponseEntity<String> testConnectivity() {
        try {
            // 测试内网 IP 地址
            ResponseEntity<String> internalIpResponse = testUrl(INTERNAL_IP);
            // 测试域名
            ResponseEntity<String> domainNameResponse = testUrl(DOMAIN_NAME);

            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("Internal IP Test Result: ").append(internalIpResponse.getBody()).append("\n");
            responseBuilder.append("Domain Name Test Result: ").append(domainNameResponse.getBody());

            return ResponseEntity.ok().body(responseBuilder.toString());
        } catch (Exception e) {
            // 如果发生异常，返回错误信息
            return ResponseEntity.status(500).body("Failed to test connectivity: " + e.getMessage());
        }
    }

    private ResponseEntity<String> testUrl(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("parameters");

        try {
            // 构建完整的 URL
            String fullUrl = "http://" + url;
            ResponseEntity<String> response = restTemplate.exchange(fullUrl, HttpMethod.GET, request, String.class);
            return response;
        } catch (Exception e) {
            // 如果发生异常，返回一个错误响应
            return ResponseEntity.status(500).body("Failed to connect to " + url + ": " + e.getMessage());
        }
    }
}
