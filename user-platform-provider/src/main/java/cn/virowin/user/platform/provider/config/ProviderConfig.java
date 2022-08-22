package cn.virowin.user.platform.provider.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author virowin
 * @date 2022/8/22 14:55
 */
@Configuration
@EnableDubbo(scanBasePackages = {"cn.virowin.user.platform.provider"})
@PropertySource("classpath:dubbo-provider.properties")
public class ProviderConfig {
}
