package wkyyzl.cn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;
import wkyyzl.cn.bean.Pet;
import wkyyzl.cn.converter.CustomizeMessageConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    //WebMvcConfigurer定制化SpringMVC的功能
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {

            /**
             * 配置内容协商选项
             * @param configurer
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                //指定支持解析哪些参数对应的哪些媒体类型
                HashMap<String, MediaType> mediaTypeHashMap = new HashMap<>();
                mediaTypeHashMap.put("json", MediaType.APPLICATION_JSON);
                mediaTypeHashMap.put("xml", MediaType.APPLICATION_XML);
                mediaTypeHashMap.put("wky", MediaType.parseMediaType("application/x-wky"));
                ParameterContentNegotiationStrategy parameterContentNegotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypeHashMap);
                //url?format=json/xml/wky
                //修改format为自定义ff
                //parameterContentNegotiationStrategy.setParameterName("ff");

                //添加请求头内容类型协商
                HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(parameterContentNegotiationStrategy, headerContentNegotiationStrategy));
            }

            /**
             * 添加自定义消息转换器(不覆盖默认转换器)
             * configureMessageConverters：配置自定义消息转换器(会覆盖默认转换器)
             * @param converters
             */
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                CustomizeMessageConverter customizeMessageConverter = new CustomizeMessageConverter();
                converters.add(customizeMessageConverter);
            }

            /**
             * 帮助配置 HandlerMapping 路径匹配选项，例如是否使用已解析的 PathPatterns 或 String 模式与 PathMatcher 匹配，是否匹配尾部斜杠等。
             * @param configurer
             */
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                //改为不移除";"后面的内容，矩阵变量即生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            /**
             * 除了默认注册的转换器和格式化程序外，还添加转换器和格式化程序。
             */
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String source) {
                        //阿猫,3
                        if(!StringUtils.isEmpty(source)){
                            Pet pet = new Pet();
                            String[] split = source.split(",");
                            pet.setName(split[0]);
                            pet.setAge(Integer.parseInt(split[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }


        };
    }



}
