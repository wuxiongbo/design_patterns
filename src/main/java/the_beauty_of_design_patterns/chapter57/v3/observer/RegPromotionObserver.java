package the_beauty_of_design_patterns.chapter57.v3.observer;

import the_beauty_of_design_patterns.chapter56.dependence.PromotionService;
import com.google.common.eventbus.Subscribe;

/**
 * <p>促销 通知， 服务实现</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class RegPromotionObserver {

    private PromotionService promotionService; // 依赖注入

    /**
     *
     * 你可能会问，每个 Observer 能接收的消息类型是在哪里定义的呢？
     * 我们来看下 Guava EventBus 最特别的一个地方，那就是 @Subscribe 注解。
     *
     * EventBus 通过 @Subscribe 注解来标明，某个函数能接收哪种类型的消息。
     *
     * 当通过 register() 函数将 RegPromotionObserver 类对象注册到 EventBus 的时候，
     * EventBus 会根据 @Subscribe 注解找到 handleRegSuccess()，
     * 并且将函数能接收的消息类型记录下来，当我们通过 post() 函数发送消息的时候，EventBus 会通过之前的记录，调用相应的函数。
     *
     * @param userId
     */
    @Subscribe
    public void handleRegSuccess(Long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }

}
