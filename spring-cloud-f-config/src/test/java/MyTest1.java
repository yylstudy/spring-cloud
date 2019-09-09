import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * @Author yang.yonglian
 * @ClassName: PACKAGE_NAME
 * @Description: TODO(这里描述)
 * @Date 2019/8/29 0029
 */
@Slf4j
public class MyTest1 {
    @Test
    public void test1(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:1119/encrypt";
        String encryptStr = restTemplate.postForObject(url,"123",String.class);
        log.info("encryptStr:{}", encryptStr);
    }
}
