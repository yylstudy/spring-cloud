package com.yyl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/26 0026
 */
@Slf4j
@RestController
public class FeignController {
    @Autowired
    private HelloClient helloClient;
    @Autowired
    private HelloClient2 helloClient2;

    @RequestMapping("/test1")
    public String test1(){
        long t1 = System.currentTimeMillis();
        String result = helloClient.hello();
        log.info("call interface result:{}",result);
        log.info("call interface time:{}",(System.currentTimeMillis()-t1));
        return "success";
    }

    /**
     * 指定feign客户端方法配置，这个是在bootstrap.yml中配置的
     * @return
     */
    @RequestMapping("/test3")
    public String test3(){
        long t1 = System.currentTimeMillis();
        String result = helloClient.hello3();
        log.info("call interface result:{}",result);
        log.info("call interface time:{}",(System.currentTimeMillis()-t1));
        return "success";
    }

    @RequestMapping("/getUser")
    public String test3(String id){
        long t1 = System.currentTimeMillis();
        String result = helloClient.getUser(id);
        log.info("call interface result:{}",result);
        log.info("call interface time:{}",(System.currentTimeMillis()-t1));
        return "success";
    }

    @RequestMapping("/getBody")
    public String getBody(){
        long t1 = System.currentTimeMillis();
        User user = new User("yyl10");
        User result = helloClient.getBody(user);
        log.info("call interface result:{}",result);
        log.info("call interface time:{}",(System.currentTimeMillis()-t1));
        return "success";
    }
    @RequestMapping("/test4")
    public String test4(){
        long t1 = System.currentTimeMillis();
        String result = helloClient2.hello();
        log.info("call interface helloClient2 result:{}",result);
        log.info("call interface helloClient2 time:{}",(System.currentTimeMillis()-t1));
        return "success";
    }

}
