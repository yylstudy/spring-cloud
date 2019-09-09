package com.yyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 单点的eureka注册中心
 * @Date 2019/8/19 0019
 */
@SpringBootApplication
@EnableEurekaServer
public class SingleEurekaServer {
    public static void main(String[] args){
        SpringApplication.run(SingleEurekaServer.class,args);
    }
}
