package design_patterns.chapter19.demo2.v3;


/**
 * <p> Notification 依赖注入的实现方式 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */

public class Notification {

    private final MessageSender messageSender;

    // 通过构造函数将messageSender传递进来。而非在构造方法里面 new。 这就是依赖注入的体现。
    // 为了更加灵活，我们将入参抽象为接口。
    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        //...省略校验逻辑等...
        this.messageSender.send(cellphone, message);
    }
}
