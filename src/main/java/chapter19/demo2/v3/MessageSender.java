package chapter19.demo2.v3;

/**
 * <p>Message抽象成接口</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public interface MessageSender {
    void send(String cellphone, String message);
}
