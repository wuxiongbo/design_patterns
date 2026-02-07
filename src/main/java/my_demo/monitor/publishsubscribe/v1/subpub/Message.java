package my_demo.monitor.publishsubscribe.v1.subpub;

import lombok.Getter;
import lombok.Setter;
/**
 * 消息体。（狭义上的消息体，实际在模型中 代表的是 事件，消息订阅与发布  实际是 事件的订阅与发布 ）
 * @author Xander Wu
 * @date 2022/11/18 10:45
 */
public class Message<Msg> {
    @Getter
    @Setter
    private String publisher;
    @Getter
    @Setter
    private Msg msg;

    public Message(String publisher, Msg msg) {
        this.publisher = publisher;
        this.msg = msg;
    }
}