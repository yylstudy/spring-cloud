package com.yyl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/29 0029
 */
@RestController
@Slf4j
@RefreshScope
public class MyController {
    @Value("${user.username}")
    private String username;

    @Value("${user.age}")
    private Integer age;

    @Value("${spring.cloud.version}")
    private String version;

    @RequestMapping("/test1")
    public String test1(){
        log.info("age:{},username:{},version:{}",age,username,version);
        return "success";
    }
}
