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
}
