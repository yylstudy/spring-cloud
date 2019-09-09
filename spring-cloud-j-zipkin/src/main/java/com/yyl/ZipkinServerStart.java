package com.yyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/9/1 0001
 */
@SpringBootApplication
@EnableZipkinStreamServer
public class ZipkinServerStart {
    public static void main(String[] args){
        SpringApplication.run(ZipkinServerStart.class, args);
    }
}
