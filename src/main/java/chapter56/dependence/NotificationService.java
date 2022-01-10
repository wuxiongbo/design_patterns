package chapter56.dependence;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public interface NotificationService {
    void sendInboxMessage(long userId, String s);
}
