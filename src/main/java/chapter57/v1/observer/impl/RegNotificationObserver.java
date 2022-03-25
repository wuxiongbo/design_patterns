package chapter57.v1.observer.impl;

import chapter56.dependence.NotificationService;
import chapter57.v1.observer.RegObserver;

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
