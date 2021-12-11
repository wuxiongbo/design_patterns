package my_demo.monitor.event.interfaces;

/**
 * <p>事件接口</p>
 *  通常对于事件对象，我们一般需要从事件对象中获取到事件源对象
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface IEvent {
    //声明事件类型。
    String CREATE_EVENT = "create event";
    String UPDATE_EVENT = "update event";
    String RETRIEVE_EVENT = "retrieve event";
    String DELETE_EVENT = "delete event";

    //获取事件源对象
    IEventSource getEventSource();

    //获取事件类型
    String getEventType();
}
