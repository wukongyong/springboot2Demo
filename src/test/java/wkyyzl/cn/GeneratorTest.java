package wkyyzl.cn;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class GeneratorTest {

    String outputDir = "E://data//workspace//gitProject//springboot2Demo//src//main//java//";

    @Test
    void testGenerator() {
        // 数据库连接、用户名、密码
        FastAutoGenerator.create("jdbc:mysql://101.43.66.224:3306/nettyTest", "root", "Wky20133828.")
                .globalConfig(builder -> {
                    builder.author("wukongyong") // 设置作者
                            .disableOpenDir()//不打开写路径
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("wkyyzl.cn") // 设置父包名
                            .entity("bean")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tb_Device") // 设置需要生成的表名
                            .addTablePrefix("tb_") // 设置过滤表前缀

                            .entityBuilder()
                            .disableSerialVersionUID()
                            .enableLombok()
                            .enableActiveRecord();
                })
                .templateConfig(builder -> {
                    builder.entity("templates/myEntity.java");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
