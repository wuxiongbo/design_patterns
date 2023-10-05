package the_beauty_of_design_patterns.chapter29.demo1.v3;

import the_beauty_of_design_patterns.chapter29.demo1.dependence.RedisDistributedLock;

/**
 * <p>封装上锁逻辑</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
public class TransactionLock {

    public boolean lock(String id) {
        return RedisDistributedLock.getSingletonInstance().lockTransction(id);
    }

    public void unlock(String id) {
        RedisDistributedLock.getSingletonInstance().unlockTransction(id);
    }

}
