package com.yyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/26 10:47
 * @Version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class MGaeger {
    public static void main(String[] args) {
        SpringApplication.run(MGaeger.class,args);
    }
}
