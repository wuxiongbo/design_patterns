package the_beauty_of_design_patterns.chapter19.demo2.v2;

/**
 * <p> Notification 依赖注入的实现方式 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */

public class Notification {
    private MessageSender messageSender;

    // 通过构造函数将messageSender传递进来。而非在构造方法里面new
    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        //...省略校验逻辑等...
        this.messageSender.send(cellphone, message);
    }
}
