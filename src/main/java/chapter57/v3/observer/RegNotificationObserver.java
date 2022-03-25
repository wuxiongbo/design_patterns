package chapter57.v3.observer;

import chapter56.dependence.NotificationService;
import com.google.common.eventbus.Subscribe;

/**
 * <p>注册 通知， 服务实现</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class RegNotificationObserver {
    private NotificationService notificationService;

    @Subscribe
    public void handleRegSuccess(Long userId) {
        notificationService.sendInboxMessage(userId, "...");
    }
}
