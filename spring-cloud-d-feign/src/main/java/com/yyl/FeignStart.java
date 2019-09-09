package com.yyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/26 0026
 */
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class FeignStart {
    public static void main(String[] args){
        SpringApplication.run(FeignStart.class,args);
    }
}
