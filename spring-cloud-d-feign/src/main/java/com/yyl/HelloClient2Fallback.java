package com.yyl;

import org.springframework.stereotype.Component;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/27 0027
 */
@Component
public class HelloClient2Fallback implements HelloClient2{
    @Override
    public String hello() {
        return "server is error please wait helloClient2";
    }
}
