package wkyyzl.cn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration(proxyBeanMethods = false)
public class WebConfig {

    /**
     * 规范开发风格为MVC的REST风格
     * @return
     */
    /*@Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        //methodFilter.setMethodParam("_m");
        return methodFilter;
    }*/

}
