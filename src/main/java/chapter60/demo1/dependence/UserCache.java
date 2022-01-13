package chapter60.demo1.dependence;

import chapter59.dependence.User;
import chapter60.demo1.strategy.EvictionStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/12
 * </pre>
 */
public class UserCache {

    private Map<String, User> cacheData = new HashMap<>();

    private EvictionStrategy eviction;

    public UserCache(EvictionStrategy eviction) {
        this.eviction = eviction;
    }

    //...
}
