package wkyyzl.cn.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wkyyzl.cn.bean.Result;

@RestControllerAdvice
public class SpringMVCExceptionHandler {

    /**
     * 拦截所有的异常信息
     *
     * @param e
     * @return
     * @ExceptionHandler(Exception.class)可以指定获取异常类型
     */
    @ExceptionHandler
    public Result doException(Exception e) {
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员
        e.printStackTrace();
        return new Result(false, null, e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Result handleUsernameNotFoundException() {
        return new Result(false, null, "用户名不存在!!!");
    }

}
