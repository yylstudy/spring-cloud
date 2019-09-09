package com.yyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/9/1 0001
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SleuthStart {
    public static void main(String[] args){
        SpringApplication.run(SleuthStart.class, args);
    }
}
