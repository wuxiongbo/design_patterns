package the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.factory;

import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod.INotificationFactory;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl.UrgencyNotificationFactory;
import the_beauty_of_design_patterns.chapter16.dependence.NotificationEmergencyLevel;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl.NormalNotificationFactory;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl.TrivialNotificationFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>工厂的工厂</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class NotificationFactoryCreator {

    private static final Map<NotificationEmergencyLevel, INotificationFactory> NOTIFICATIONS = new HashMap<>();

    static {
        NOTIFICATIONS.put(NotificationEmergencyLevel.SEVERE,new NormalNotificationFactory());
        NOTIFICATIONS.put(NotificationEmergencyLevel.URGENCY,new UrgencyNotificationFactory());
        NOTIFICATIONS.put(NotificationEmergencyLevel.NORMAL,new NormalNotificationFactory());
        NOTIFICATIONS.put(NotificationEmergencyLevel.TRIVIAL,new TrivialNotificationFactory());
    }

    /**
     * 饿加载、单例
     * @param notificationEmergencyLevel
     * @return
     */
    public static INotificationFactory getNotificationFactory(NotificationEmergencyLevel notificationEmergencyLevel){
        // ...校验
        return NOTIFICATIONS.get(notificationEmergencyLevel);
    }
}
