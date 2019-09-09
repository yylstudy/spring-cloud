package com.yyl;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 局部禁用feign的hystrix
 * @Date 2019/8/26 0026
 */
public class DisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }

}
