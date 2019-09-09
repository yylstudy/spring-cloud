package com.yyl;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/27 0027
 */
@Slf4j
@Component
public class HelloClientFallbackFactory implements FallbackFactory<HelloClient> {
    @Override
    public HelloClient create(Throwable throwable) {
        return new HelloClient() {
            @Override
            public String hello() {
                log.error("call interface hello error",throwable);
                return "server is error please wait hello";
            }

            @Override
            public String hello3() {
                log.error("call interface hello hello3",throwable);
                return "server is error please wait hello3";
            }

            @Override
            public String getUser(String id) {
                log.error("call interface hello getUser",throwable);
                return "server is error please wait getUser";
            }

            @Override
            public User getBody(User user) {
                log.error("call interface hello getBody",throwable);
                return new User("yyl-1");
            }
        };
    }
}
