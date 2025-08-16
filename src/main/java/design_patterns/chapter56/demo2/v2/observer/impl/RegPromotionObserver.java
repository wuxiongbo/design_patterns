package design_patterns.chapter56.demo2.v2.observer.impl;

import design_patterns.chapter56.demo2.v2.observer.RegObserver;
import design_patterns.chapter56.dependence.PromotionService;

/**
 * <p>注册成功，发放优惠券</p>
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
        promotionService.issueNewUserExperienceCash(userId);
    }
}