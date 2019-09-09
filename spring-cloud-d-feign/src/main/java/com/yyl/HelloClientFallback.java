package com.yyl;

import org.springframework.stereotype.Component;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 服务降级的feign客户端实现类
 * @Date 2019/8/26 0026
 */
@Component
public class HelloClientFallback implements HelloClient{
    @Override
    public String hello() {
        return "server is error please wait hello";
    }

    @Override
    public String hello3() {
        return "server is error please wait hello3";
    }

    @Override
    public String getUser(String id) {
        return "server is error please wait getUser";
    }

    @Override
    public User getBody(User user) {
        return new User("yyl-1");
    }
}
