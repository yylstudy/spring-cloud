package com.yyl;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/23 0023
 */
@RestController
@Slf4j
public class HystrixController {
    @Autowired
    private HystrixService hystrixService;
    @RequestMapping("/test1")
    public String test1(String name){
        long t1 = System.currentTimeMillis();
        String result = hystrixService.hello(name);
        log.info("invoke interface result:{}",result);
        long t2 = System.currentTimeMillis();
        log.info("invoke interface time:{}",(t2-t1));
        return "success";
    }

    @RequestMapping("/test2")
    public String test2(String name){
        String result = hystrixService.hello2(name);
        log.info("invoke interface result:{}",result);
        return "success";
    }

    @RequestMapping("/test3")
    public String test3(String name){
        long t1 = System.currentTimeMillis();
        String result = hystrixService.hello3(name);
        log.info("invoke interface result:{}",result);
        long t2 = System.currentTimeMillis();
        log.info("invoke interface time:{}",(t2-t1));
        return "success";
    }

    /**
     * 使用断路器
     * @param name
     * @return
     */
    @RequestMapping("/test4")
    public String test4(String name){
        long t1 = System.currentTimeMillis();
        String result = hystrixService.hello4(name);
        log.info("invoke interface result:{}",result);
        long t2 = System.currentTimeMillis();
        log.info("invoke interface time:{}",(t2-t1));
        return "success";
    }



    /**
     * 模拟1000次请求，因为所有请求都失败，出发了熔断，所以请求并没有都调用helloHystrix接口
     * 实际上报如下错误，Hystrix circuit short-circuited and is OPEN，表示熔断已开，不再调用远程方法，直接调用
     * fallbackMethod，所以接口耗时很低
     * @param name
     * @return
     */
    @RequestMapping("/helloHystrix")
    public String helloHystrix(String name){
        ExecutorService pool = Executors.newFixedThreadPool(30);
        for(int i=0;i<100;i++){
            pool.submit(()->{
                long t1 = System.currentTimeMillis();
                String result = hystrixService.helloHystrix(name);
                log.info("invoke interface result:{}",result);
                long t2 = System.currentTimeMillis();
                log.info("invoke interface time:{}",(t2-t1));
            });
        }
        return "success";
    }

    /**
     * 使用hystrix缓存 注意hystrix的缓存是请求级别的缓存，也就是说在同一个http请求内缓存才有效
     * @param name
     * @return
     */
    @RequestMapping("/helloCache")
    public String helloCache(String name){
        HystrixRequestContext context = null;
        try{
            //单个请求初始化 HystrixRequestContext，或者可采用全局Filter的方式初始化
            context = HystrixRequestContext.initializeContext();
            long t1 = System.currentTimeMillis();
            User user = new User(name);
            String result = hystrixService.helloCache(user);
            String result2 = hystrixService.helloCache(user);
            log.info("invoke interface result:{}",result);
            log.info("invoke interface result:{}",result2);
            //刷新缓存
            hystrixService.cacheUpdate(user);
            String result3 = hystrixService.helloCache(user);
            log.info("invoke interface result:{}",result3);
            long t2 = System.currentTimeMillis();
            log.info("invoke interface time:{}",(t2-t1));
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if(context!=null){
                context.shutdown();
            }
        }

        return "success";
    }


    /**
     * 使用hystrix请求合并，请求合并的缺点:
     * 虽然请求合并可以减少请求次数，但是在使用的时候会造成额外的开销：用于请求合并的延时时间窗会使得依赖服务的
     * 请求延迟增高，默认为10毫秒，所以我们是否使用请求合并器主要根据两方面：
     * 1）请求的服务本身延迟比较高时，可以使用合并器
     * 2）延迟时间窗内的请求并发量，也就是默认10毫秒内如果只有1-2个请求那么不适合使用请求合并器，
     * 另外也需要业务提供方提供批量的处理接口
     * @param name
     * @return
     */
    @RequestMapping("/hystrixCollapser")
    public String hystrixCollapser(String name) throws Exception{
        //单个请求初始化 HystrixRequestContext，或者可采用全局Filter的方式初始化
        Future<String> username4 = hystrixService.getUser("4");
        Future<String> username3 = hystrixService.getUser("3");
        Future<String> username1 = hystrixService.getUser("1");
        log.info("invoke interface result:{},{},{}",username4.get(),username3.get(),username1.get());
        //睡眠1s，达到合并时间窗时间，所以这后面又会再发起一次请求
        Thread.sleep(500);
        Future<String> username2 = hystrixService.getUser("2");
        log.info("invoke interface result:{}",
                username2.get());
        return "success";
    }


}
