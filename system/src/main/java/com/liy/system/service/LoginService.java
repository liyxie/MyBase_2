package com.liy.system.service;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @Author LiY
 * 登录服务
 */

@Service
public interface LoginService {

    /**
     * @description: 生成验证码图片
     * @author: liy
     * @param: uuid：随机id
     * @return:  BufferedImage：验证码图片
     **/
    BufferedImage getCaptchaImg(String uuid);

    /**
     * @description: 用户登录，生成token
     * @author: liy
     * @param:  userName，password，code
     * @return:  token:String
     **/
    String login(String userName, String password, String uuid, String code);

}
