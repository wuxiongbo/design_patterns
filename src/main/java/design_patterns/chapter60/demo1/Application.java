package design_patterns.chapter60.demo1;

import design_patterns.chapter60.demo1.dependence.UserCache;
import design_patterns.chapter60.demo1.strategy.EvictionStrategy;
import design_patterns.chapter60.demo1.strategyfactory.EvictionStrategyFactory;

import java.io.FileInputStream;
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
        Properties props = new Properties();
        props.load(new FileInputStream("./config.properties"));
        String type = props.getProperty("eviction_type");


        // 获取策略
        evictionStrategy = EvictionStrategyFactory.getEvictionStrategy(type);

        // 使用策略
        UserCache userCache = new UserCache(evictionStrategy);
        //...
    }
}
