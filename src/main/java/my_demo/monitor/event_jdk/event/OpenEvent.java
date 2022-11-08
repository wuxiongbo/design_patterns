package my_demo.monitor.event_jdk.event;

/**
 * @author Xander Wu
 * @date 2022/11/4 18:10
 */
public class OpenEvent extends PrintEvent {
    /**
     * @param eventSource 事件源； 本示例中，是 {@link my_demo.monitor.event_jdk.eventsource.WindowsEventSource}
     * @param eventName
     */
    public OpenEvent(Object eventSource, String eventName) {
        super(eventSource, eventName);
    }

    public OpenEvent(Object eventSource) {
        this(eventSource, "Open");
    }
}
