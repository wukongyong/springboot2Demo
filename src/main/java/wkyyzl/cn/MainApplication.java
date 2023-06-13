package wkyyzl.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import wkyyzl.cn.netty.NettyWebSocketServer;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        //1.返回ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2.查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        /*for(String s : names){
            System.out.println(s);
        }*/

        //启动netty服务器
        NettyWebSocketServer nettyWebSocketServer = new NettyWebSocketServer(4020);
        nettyWebSocketServer.run();

        System.out.println("---------------");
    }

}
