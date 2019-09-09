package com.yyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/29 0029
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientStart {
    public static void main(String[] args){
        SpringApplication.run(ConfigClientStart.class, args);
    }
}
