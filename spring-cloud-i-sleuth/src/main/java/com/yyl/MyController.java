package com.yyl;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/9/1 0001
 */
@RestController
@Slf4j
public class MyController {
    @Autowired
    private MyFeign myFeign;
    @RequestMapping("/test1")
    public String test1(){
        String result = myFeign.getBody();
        log.info("result:{}", result);
        return "success";
    }

}
