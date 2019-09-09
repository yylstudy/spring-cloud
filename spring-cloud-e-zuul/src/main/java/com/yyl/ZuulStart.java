package com.yyl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/27 0027
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableHystrix
@RestController
@Slf4j
public class ZuulStart {
    public static void main(String[] args){
        SpringApplication.run(ZuulStart.class,args);
    }

    @ConfigurationProperties("zuul")
    @RefreshScope
    @Bean
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }
    @Value("${zuul.routes.feign-client.serviceId}")
    private String ss;

    @RequestMapping("/test1")
    public String test1(){
        log.info("-----------------------:{}",ss);
        return "success";
    }
}
