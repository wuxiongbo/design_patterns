package chapter18.config;

import chapter18.scheduled.Updater;
import chapter18.util.ConfigSource;
import chapter18.view.Viewer;

import java.util.Map;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class RedisConfig implements Updater, Viewer {
    private ConfigSource configSource; //配置中心（比如zookeeper）

    // 一些redis 的配置信息
    private String address;
    private int timeout;
    private int maxTotal;
    //省略其他配置: maxWaitMillis,maxIdle,minIdle...



    public RedisConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    public String getAddress() {
        return this.address;
    }
    //...省略其他get()、init()方法...


    /**
     * 继承Updater接口。实现 update 热更新功能
     */
    @Override
    public void update() {
        // 热更新的实现。

        // 从configSource（比如zookeeper）加载配置到 address/timeout/maxTotal...

    }

    /**
     * 继承Viewer接口。实现 outputInPlainText、output 功能
     * @return
     */
    @Override
    public String outputInPlainText() {
        return null;
    }

    @Override
    public Map<String, String> output() {
        return null;
    }
}
