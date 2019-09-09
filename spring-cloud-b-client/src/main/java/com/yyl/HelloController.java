package com.yyl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/19 0019
 */
@RestController
@Slf4j
public class HelloController {
    private static Map<String,String> users = new HashMap();

    @PostConstruct
    public void initUser(){
        users.put("1", "yyl1");
        users.put("2", "yyl2");
        users.put("3", "yyl3");
        users.put("4", "yyl4");
        users.put("5", "yyl5");
    }

    @Autowired
    private DiscoveryClient discoveryClient;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    /**
     * 获取注册中心的所有服务的所有实例
     * @return
     */
    @RequestMapping("/getInstance")
    public String getInstance(){
        List<String> serviceList =  discoveryClient.getServices();
        log.info("服务列表:{}",serviceList);
        List<ServiceInstance> instances = serviceList.stream()
                .flatMap(service->discoveryClient.getInstances(service).stream()).collect(Collectors.toList());
        log.info("服务实例:{}",instances);
        return "hello world";
    }
    @RequestMapping("/helloHystrix")
    public String helloHystrix() throws Exception{
        log.info("receive request start");
        atomicInteger.incrementAndGet();
        log.info("atomicInteger:{}",atomicInteger);
        Thread.sleep(5000);
        log.info("receive request end");
        return "hello world";
    }


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() throws Exception{
        log.info("receive request start");
        Thread.sleep(5000);
        log.info("receive request end");
        return "hello world";
    }

    @RequestMapping("/hello3")
    public String hello3() throws Exception{
        log.info("receive request start");
        Thread.sleep(1500);
        log.info("receive request end");
        return "hello world3";
    }
    @RequestMapping("/getUser")
    public String getUser(String id){
        log.info("receive request id:{}",id);
        return users.get(id);
    }

    @RequestMapping("/getUser/{id}")
    public String getUserPathVariable(@PathVariable("id") String id){
        log.info("receive request id:{}",id);
        return users.get(id);
    }

    @RequestMapping("/getBody")
    public User getBody(@RequestBody User user){
        log.info("receive request username:{}",user.getUsername());
        return user;
    }



    @RequestMapping("/getUsers")
    public List<String> getUsers(String ids){
        log.info("receive request ids:{}",ids);
        return Arrays.stream(ids.split(",")).map(users::get).collect(Collectors.toList());
    }

}
