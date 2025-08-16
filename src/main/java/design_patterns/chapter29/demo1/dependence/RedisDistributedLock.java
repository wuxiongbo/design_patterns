package design_patterns.chapter29.demo1.dependence;

/**
 * <p>单例</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
public class RedisDistributedLock {

    public boolean lockTransction(String id) {
        return false;
    }

    public void unlockTransction(String id) {
    }

    // 外部类 IdGenerator 被加载的时候，并不会创建 SingletonHolder 实例对象
    private static class SingletonHolder {
        private static final RedisDistributedLock INSTANCE = new RedisDistributedLock();
    }

    public static RedisDistributedLock getSingletonInstance() {
        // 只有调用 SingletonHolder时，SingletonHolder 才会被加载，这个时候才会创建 INSTANCE
        return SingletonHolder.INSTANCE;
    }


}
