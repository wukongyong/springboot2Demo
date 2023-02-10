package wkyyzl.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {

    static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        //1.返回ioc容器
        context = SpringApplication.run(MainApplication.class, args);

        //2.查看容器里面的组件
        String[] names = context.getBeanDefinitionNames();
        for(String s : names){
            System.out.println(s);
        }
    }

    public ConfigurableApplicationContext test(){
        return context;
    }

}
