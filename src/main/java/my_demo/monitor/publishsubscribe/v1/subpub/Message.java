package my_demo.monitor.publishsubscribe.v1.subpub;

/**
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