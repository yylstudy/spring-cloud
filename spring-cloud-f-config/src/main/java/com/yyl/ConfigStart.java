package com.yyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/28 0028
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
@RestController
public class ConfigStart {
    public static void main(String[] args){
        SpringApplication.run(ConfigStart.class, args);
    }
    @RequestMapping("/test1")
    @ResponseBody
    public String test1(){
        return "success";
    }
}
