package wkyyzl.cn.config;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

// 配置的前缀
@Configuration
public class ElasticsearchConfig {


        /**
         * 多个IP逗号隔开
         */
        @Value("${spring.elasticsearch.uris}")
        private String uris;

        /**
         * 同步方式
         *
         * @return
         */
        @Bean
        public ElasticsearchClient elasticsearchClient() {
            HttpHost[] httpHosts = toHttpHost();
            // Create the RestClient
            RestClient restClient = RestClient.builder(httpHosts).build();
            // Create the transport with a Jackson mapper
            RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
            // create the API client
            return new ElasticsearchClient(transport);
        }

        /**
         * 异步方式
         *
         * @return
         */
        @Bean
        public ElasticsearchAsyncClient elasticsearchAsyncClient() {
            HttpHost[] httpHosts = toHttpHost();
            RestClient restClient = RestClient.builder(httpHosts).build();
            RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
            return new ElasticsearchAsyncClient(transport);
        }

        /**
         * 解析配置的字符串hosts，转为HttpHost对象数组
         *
         * @return
         */
        private HttpHost[] toHttpHost() {
            if (!StringUtils.hasLength(uris)) {
                throw new RuntimeException("invalid elasticsearch configuration. elasticsearch.hosts不能为空！");
            }

            // 多个IP逗号隔开
            String[] hostArray = uris.split(",");
            HttpHost[] httpHosts = new HttpHost[hostArray.length];
            HttpHost httpHost;
            for (int i = 0; i < hostArray.length; i++) {
                String[] strings = hostArray[i].split(":");
                httpHost = new HttpHost(strings[0], Integer.parseInt(strings[1]), "http");
                httpHosts[i] = httpHost;
            }

            return httpHosts;
        }

}
