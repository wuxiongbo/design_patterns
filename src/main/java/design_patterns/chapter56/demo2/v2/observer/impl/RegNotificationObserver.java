package design_patterns.chapter56.demo2.v2.observer.impl;

import design_patterns.chapter56.demo2.v2.observer.RegObserver;
import design_patterns.chapter56.dependence.NotificationService;

/**
 * <p>注册成功，欢迎语</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class RegNotificationObserver implements RegObserver {

    private NotificationService notificationService;

    @Override
    public void handleRegSuccess(long userId) {
        notificationService.sendInboxMessage(userId, "Welcome...");
    }
}
