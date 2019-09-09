package com.yyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/19 0019
 */
@SpringBootApplication
@EnableEurekaClient
public class MyEurekaClient {

    public static void main(String[] args){
        SpringApplication.run(MyEurekaClient.class,args);
    }

}
