package cn.virowin.user.platform.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * @author virowin
 * @date 2022/8/20 15:53
 */
@SpringBootApplication(scanBasePackages = {"cn.virowin.user.platform"},
        exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class MainApplication {
    public static void main(String[] args) throws Exception {
        new EmbeddedZooKeeper(2181, false).start();
        SpringApplication.run(MainApplication.class, args);
    }
}