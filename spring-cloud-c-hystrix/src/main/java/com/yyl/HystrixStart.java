package com.yyl;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 主要启用以下注解
 * 1)启用eureka的客户端
 * 2)开启熔断
 * 3)启用扫描Servlet注解
 * 4)开启熔断仪表盘
 * @Date 2019/8/23 0023
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@ServletComponentScan
@EnableHystrixDashboard
public class HystrixStart {
    public static void main(String[] args){
        SpringApplication.run(HystrixStart.class,args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
