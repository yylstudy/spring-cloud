package com.yyl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/23 0023
 */
@Service
@Slf4j
public class HystrixService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用断路器
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(String name){
        String result = restTemplate.getForObject("http://client1/hello", String.class);
        return result;
    }

    /**
     * 使用全局设置断路器的超时时间
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello2(String name){
        String result = restTemplate.getForObject("http://client1/hello3", String.class);
        return result;
    }


    /**
     * 单个方法配置断路器的超时时间
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloError",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"))
    public String hello3(String name){
        String result = restTemplate.getForObject("http://client1/hello3", String.class);
        return result;
    }

    /**
     * 使用实例配置属性来配置断路器，这个是在bootstrap.yml中配置
     * commandKey对应配置文件中的command名称
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloError",commandKey = "hello4")
    public String hello4(String name){
        String result = restTemplate.getForObject("http://client1/hello3", String.class);
        return result;
    }


    /**
     * 使用断路器
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloError2")
    public String helloHystrix(String name){
        String result = restTemplate.getForObject("http://client1/helloHystrix", String.class);
        return result;
    }

    /**
     * 使用hystrix 缓存注解，其中的cacheKeyMethod类似hystrix的fallbackMethod方法
     * 表示该缓存的key，该注解的优先级高于@CacheKey，如下表示 指定了User对象的username属性作为缓存的key
     * @param user
     * @return
     */
    @CacheResult
    @HystrixCommand(fallbackMethod = "helloErrorObject")
    public String helloCache(@CacheKey("username") User user){
        String result = restTemplate.getForObject("http://client1/hello3", String.class);
        return result;
    }

    /**
     * 缓存清理 @CacheRemove是缓存清理注解，其中的commandKey是必填的，表示要清理缓存的方法或者对应的
     * @HystrixCommand 的commandKey
     * @param user
     * @return
     */
    @CacheRemove(commandKey = "helloCache")
    @HystrixCommand(fallbackMethod = "helloErrorObject")
    public String cacheUpdate(@CacheKey("username") User user){
        String result = restTemplate.getForObject("http://client1/hello3", String.class);
        return result;
    }

    /**
     * 获取单个用户，这里使用了HystrixCollapser注解实现请求的合并，
     * timerDelayInMilliseconds是设置合并时间窗时间 默认为10毫秒
     * 注意这个方法的返回值是Future
     * @return
     */
    @HystrixCollapser(batchMethod = "getUsers",
            collapserProperties=@HystrixProperty(name="timerDelayInMilliseconds",value = "100"))
    public Future<String> getUser(String id){
        return null;
    }

    /**
     * 获取多个用户
     * @param ids
     * @return
     */
    @HystrixCommand
    public List<String> getUsers(List<String> ids){
        List<String> users = restTemplate.getForObject("http://client1/getUsers?ids={1}",
                List.class,ids.stream().collect(Collectors.joining(",")));
        return users;
    }



    public String helloErrorObject(User user,Throwable t){
        log.error(user+" call client1 server failure ",t);
        return "server is error";
    }

    public String helloError(String name,Throwable t){
        log.error(name+" call client1 server failure ",t);
        return "server is error";
    }

    public String helloError2(String name,Throwable t){
        log.error("call error",t);
        return "server is error";
    }


}
