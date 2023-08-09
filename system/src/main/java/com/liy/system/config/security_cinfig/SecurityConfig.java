package com.liy.system.config.security_cinfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Author LiY
 * Security 配置类
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    //白名单
    private static final String[] DOC = {"/","/index", "/druid", "/springdoc/index.html", "/sys/*/test", "/captchaImg", "/login"};

    // JWT 处理过滤
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //认证失败处理
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    // 权限不足处理
//    @Autowired
//    private

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security
                // 设置白名单
                .authorizeHttpRequests(resp -> resp
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/druid/login.html", "/druid").permitAll()
                        // 静态资源，可匿名访问
                        .requestMatchers(HttpMethod.GET, "/", "/*/*/*.html", "/*/*/*.css",
                                "/*/*.js", "/*/*.html", "/*/*.css", "/*/*.js", "/profile/**").permitAll()
                        // Swagger3
                        .requestMatchers(HttpMethod.GET, "/springdoc/**", "/swagger-ui/*", "/swagger-ui/*.html", "/v3/*", "/v3/*/*").permitAll()
                        // 登录相关
                        .requestMatchers("/register", "/sys/captchaImg", "/sys/login").permitAll()


                        .requestMatchers("/test1").hasRole("admin")

                        .anyRequest().authenticated()
                )

                // JWT 过滤
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)

                // 认证失败处理
                .exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint))

                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .cors(withDefaults())

                .csrf(AbstractHttpConfigurer::disable)

                // Session设为无状态
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return security.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * @description: 密码加密
     * @author: liy
     * @param:
     * @return:  PasswordEncoder：加密器
     **/
    @Bean
    public PasswordEncoder passwordEncoder(){
//        默认BCryptPasswordEncoder
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
            return new BCryptPasswordEncoder();
    }

}
