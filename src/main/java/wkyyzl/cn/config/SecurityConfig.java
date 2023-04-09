package wkyyzl.cn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import wkyyzl.cn.security.MyAuthenticationEntryPoint;
import wkyyzl.cn.security.MyAuthenticationFailureHandler;
import wkyyzl.cn.service.impl.UserServiceImpl;

@Configuration
public class SecurityConfig {

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        //用"{noop}"或者withDefaultPasswordEncoder设置不加密校验密码
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    //配置springsecurity相关信息
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("自定义的过滤器链");
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()    //其他请求，都需要认证

                .and()
                .formLogin()
                .successForwardUrl("/login")
                .failureHandler(myAuthenticationFailureHandler)

                /*http.httpBasic();*/

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)

                //一步注册，自定义数据源
                .and()
                .userDetailsService(userService);

        return http.build();
    }

}
