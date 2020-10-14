package com.yyl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/26 11:12
 * @Version: 1.0
 */
@FeignClient("feign-client")
public interface HelloClient {
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    String getUser(@RequestParam("id")String id);
}
