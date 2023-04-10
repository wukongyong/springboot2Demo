package wkyyzl.cn.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import wkyyzl.cn.bean.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Result result = new Result(false, null, "");
        if (e instanceof LockedException) {
            result.setMessage("账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            result.setMessage("密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            result.setMessage("账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            result.setMessage("账户被禁用，请联系管理员!");
        } else if (e instanceof BadCredentialsException) {
            result.setMessage("用户名或者密码输入错误，请重新输入!");
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
