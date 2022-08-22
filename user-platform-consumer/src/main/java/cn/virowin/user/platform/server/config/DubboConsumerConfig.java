package cn.virowin.user.platform.server.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author virowin
 * @date 2022/8/22 15:17
 */
@Configuration
@EnableDubbo(scanBasePackages = "cn.virowin.user.platform.server")
@PropertySource("classpath:dubbo-consumer.properties")
public class DubboConsumerConfig {
}