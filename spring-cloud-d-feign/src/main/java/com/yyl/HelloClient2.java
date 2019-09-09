package com.yyl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: feign客户端
 * @Date 2019/8/26 0026
 */
@FeignClient(value = "client1",fallback = HelloClient2Fallback.class)
public interface HelloClient2 {
    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello();

}
