package my_demo.reactor.other;

public class StateRecord {
    public static final int READING = 0;
    public static final int SENDING = 1;
    public volatile int state = READING;

    public StateRecord() {
    }
}