package chapter16.demo1.module.notify.v1.notification.factory;

import chapter16.demo1.module.notify.v1.notification.factory.factorymethod.INotificationFactory;
import chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl.UrgencyNotificationFactory;
import chapter16.demo1.module.notify.v1.notification.NotificationEmergencyLevel;
import chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl.NormalNotificationFactory;
import chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl.TrivialNotificationFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>简单工厂</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class NotificationFactoryMap {

    private static final Map<NotificationEmergencyLevel, INotificationFactory> NOTIFICATIONS = new HashMap<>();

    static {
        NOTIFICATIONS.put(NotificationEmergencyLevel.SEVERE,new NormalNotificationFactory());
        NOTIFICATIONS.put(NotificationEmergencyLevel.URGENCY,new UrgencyNotificationFactory());
        NOTIFICATIONS.put(NotificationEmergencyLevel.NORMAL,new NormalNotificationFactory());
        NOTIFICATIONS.put(NotificationEmergencyLevel.TRIVIAL,new TrivialNotificationFactory());
    }

    public static INotificationFactory getNotificationFactory(NotificationEmergencyLevel notificationEmergencyLevel){
        // ...校验
        return NOTIFICATIONS.get(notificationEmergencyLevel);
    }
}
