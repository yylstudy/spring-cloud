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
 * @Date 2019/9/1 0001
 */
@RestController
@RefreshScope
@Slf4j
public class MyController {
    @Value("${bus.name}")
    private String busName;

    @RequestMapping("/test1")
    public String test1(){
        log.info("busName:{}",busName);
        return "success";
    }
}
