package com.liy.admin;

import com.liy.common.core.RedisCache;
import com.liy.system.domain.LoginUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author LiY
 */

@SpringBootTest(classes = AdminApplication.class)
public class Reidstest {

    @Test
    public void test1(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

        LoginUser loginUser = new LoginUser();
        redisCache.setCacheObject("121212fdjfdujhfsuhufdhudshfuhdsufdufhudhfduhfu",loginUser);

    }

    @Autowired
    RedisCache redisCache;

    @Test
    public void test2(){

        String i = "dsdsdsds";
        redisCache.setCacheObject("sdadsaaaaadsdsdsds0",i);

    }

    @Test
    public void getR(){
        String l = "login_tokens:500d7588-1572-4f52-a34e-44d74de73e22";
        System.out.println(redisCache.hasKey(l));
//        System.out.println(redisCache.hasKey("captcha_codes:0d9eabe6-4dcf-48f6-b5db-038e9fa19a80"));
        System.out.println(redisCache.getCacheObject(l).toString());
        LoginUser user = redisCache.getCacheObject(l);
        System.out.println(user.toString());
    }
}
