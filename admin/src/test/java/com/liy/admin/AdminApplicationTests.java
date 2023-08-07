package com.liy.admin;

import com.liy.system.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminApplicationTests {

    @Test
    void contextLoads() {

        String pass = "admin123";
        String pass1 = SecurityUtils.encryptPassword(pass);
        String pass2 = SecurityUtils.encryptPassword(pass);
        String pas = "admin12";
        String pas3 = SecurityUtils.encryptPassword(pass);

        System.out.println(SecurityUtils.matchesPassword(pass,pass1));
        System.out.println(SecurityUtils.matchesPassword(pass,pass2));

        System.out.println(SecurityUtils.matchesPassword(pas,pas3));
        System.out.println(SecurityUtils.matchesPassword(pas, "$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2"));


    }

}
