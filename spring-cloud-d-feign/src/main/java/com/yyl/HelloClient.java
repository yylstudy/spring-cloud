package com.yyl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: feign客户端 fallback 直接调用降级方法，但是不能显示具体的错误信息，如要显示
 * 需要使用到fallbackFactory方法
 * @Date 2019/8/26 0026
 */
@FeignClient(value = "client1",
//        fallback = HelloClientFallback.class,
        fallbackFactory =HelloClientFallbackFactory.class
)
public interface HelloClient {
    @RequestMapping(value = "/hello3",method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "/hello3",method = RequestMethod.GET)
    String hello4();

    /**
     * 地址变量参数绑定方式
     * @param id
     * @return
     */
    @RequestMapping(value="/getUser",method = RequestMethod.GET)
    String getUser(@RequestParam("id") String id);

    /**
     * @RequestBody 方式绑定feign的参数
     * @return
     */
    @RequestMapping(value = "/getBody",method = RequestMethod.POST)
    User getBody(@RequestBody User user);
}
