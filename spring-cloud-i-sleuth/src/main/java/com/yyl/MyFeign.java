package com.yyl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/9/1 0001
 */
@FeignClient("feign-client")
@Component
public interface MyFeign {
    @RequestMapping("/getBody")
    String getBody();
}
