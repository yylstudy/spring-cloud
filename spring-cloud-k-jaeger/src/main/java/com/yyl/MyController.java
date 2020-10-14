package com.yyl;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/26 10:53
 * @Version: 1.0
 */
@RestController
@Slf4j
public class MyController {
    @Autowired
    private HelloClient helloClient;
    @RequestMapping("/getUser")
    public String getUser() {
        log.debug("------------------");
        return helloClient.getUser("1");
    }
}
