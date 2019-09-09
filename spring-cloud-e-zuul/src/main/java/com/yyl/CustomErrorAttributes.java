package com.yyl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 自定义异常信息
 * @Date 2019/8/28 0028
 */
@Component
@Slf4j
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> result = super.getErrorAttributes(requestAttributes, includeStackTrace);
        log.warn("excpetion info:{}",result);
        return result;
    }
}
