package chapter56.demo2.v2.observer.impl;

import chapter56.demo2.v2.observer.RegObserver;
import chapter56.dependence.NotificationService;

/**
 * <p>注册通知</p>
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
