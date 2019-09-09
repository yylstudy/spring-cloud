package com.yyl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/20 0020
 */
@RestController
@Slf4j
public class MyController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/test1")
    public String test1(){
        long t1 = System.currentTimeMillis();
        log.info("ribbot call start ");
        try{
            String result = restTemplate.getForObject("http://client1/hello",String.class);
            log.info("ribbot call end result:{}",result);
        }catch (Exception e){
            log.error("call interface failure", e);
        }
        long t2 = System.currentTimeMillis();
        log.info("call interface time:{}", (t2-t1));
        return "success";
    }


}
