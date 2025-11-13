package design_patterns.chapter60.demo1;

import design_patterns.chapter60.demo1.dependence.UserCache;
import design_patterns.chapter60.demo1.strategy.EvictionStrategy;
import design_patterns.chapter60.demo1.strategyfactory.EvictionStrategyFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * <p> 运行时 </p>
 *
 * 策略模式三部曲：
 *     定义策略        编写策略类
 *     创建策略        new 策略类
 *     使用策略        调用策略类
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/12
 * </pre>
 */
public class Application {
    public static void main(String[] args) throws Exception {

        EvictionStrategy evictionStrategy = null;

        // 运行时 动态确定，根据配置文件的配置决定使用哪种策略
        final String configPath = "chapter60/demo1/config.properties";
        Properties props = new Properties();
        try (InputStream inputStream = Application.class.getClassLoader().getResourceAsStream(configPath)) {
            if (inputStream == null) {
                throw new IllegalStateException("无法在类路径中找到配置: " + configPath);
            }
            props.load(inputStream);
        }
        String type = props.getProperty("eviction_type", "lru");


        // 获取策略
        evictionStrategy = EvictionStrategyFactory.getEvictionStrategy(type);

        // 使用策略
        UserCache userCache = new UserCache(evictionStrategy);
        //...
    }
}
