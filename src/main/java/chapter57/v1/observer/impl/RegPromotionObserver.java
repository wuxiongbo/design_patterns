package chapter57.v1.observer.impl;


import chapter56.dependence.PromotionService;
import chapter57.v1.observer.RegObserver;

/**
 * <p>注册成功，发放优惠券</p>
 *
 * 异步 第一种方式：将异步放在 观察者。
 *
 * 频繁地  创建 和 销毁 线程 比较耗时，并且 并发线程数无法控制，创建过多的线程会导致堆栈溢出。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class RegPromotionObserver implements RegObserver {

    private PromotionService promotionService; // 依赖注入

    @Override
    public void handleRegSuccess(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                promotionService.issueNewUserExperienceCash(userId);
            }
        });
        thread.start();
    }
}
