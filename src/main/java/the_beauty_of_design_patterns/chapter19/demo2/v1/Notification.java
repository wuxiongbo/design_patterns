package the_beauty_of_design_patterns.chapter19.demo2.v1;

/**
 * <p>Notification 非依赖注入实现方式</p>
 *  Notification 类负责消息推送，依赖 MessageSender 类实现推送商品促销、验证码等消息给用户。
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class Notification {
    private MessageSender messageSender;


    // 直接在 Notification 内部创建 依赖的类。
    public Notification() {
        this.messageSender = new MessageSender(); //此处有点像hardcode(硬编码)
    }

    public void sendMessage(String cellphone, String message) {
        //...省略校验逻辑等...
        this.messageSender.send(cellphone, message);
    }
}
