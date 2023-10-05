package the_beauty_of_design_patterns.chapter56.dependence;

/**
 * <p>通知 服务接口</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public interface NotificationService {
    void sendInboxMessage(long userId, String s);
}
