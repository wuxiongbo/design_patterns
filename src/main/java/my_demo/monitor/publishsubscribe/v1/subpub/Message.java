package my_demo.monitor.publishsubscribe.v1.subpub;

/**
 * 消息。（狭义上的消息体，实际在模型中 代表的是 事件，消息订阅与发布  实际是 事件的订阅与发布 ）
 * @author Xander Wu
 * @date 2022/11/18 10:45
 */
public class Message<Msg> {
    private String publisher;
    private Msg msg;

    public Message(String publisher, Msg msg) {
        this.publisher = publisher;
        this.msg = msg;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }
}