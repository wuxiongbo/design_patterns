package design_patterns.chapter18.config;

import design_patterns.chapter18.scheduled.Updater;
import design_patterns.chapter18.util.ConfigSource;
import design_patterns.chapter18.view.Viewer;

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

    /**
     * 一些redis 的配置信息
     */
    private String address;
    private int timeout;
    private int maxTotal;
    //省略其他配置: maxWaitMillis,maxIdle,minIdle...
    public String getAddress() {
        return this.address;
    }
    //...省略其他get()、init()方法...



    public RedisConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }



    /**
     * 继承自Updater接口。实现 update 热更新功能
     */
    @Override
    public void update() {
        // 热更新的实现。

        // 从 configSource（如zookeeper）加载配置  到 address/timeout/maxTotal...
    }




    /**
     * 继承自Viewer接口。实现 outputInPlainText、output 功能
     * @return
     */
    @Override
    public String outputInPlainText() {
        /*实现。。。*/
        return null;
    }
    @Override
    public Map<String, String> output() {
        /*实现。。。*/
        return null;
    }


}
