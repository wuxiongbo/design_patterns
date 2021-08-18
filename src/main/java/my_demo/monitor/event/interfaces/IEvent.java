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

    //增
    String Create_EVENT = "create event";
    //改
    String Update_EVENT = "update event";
    //查
    String Retrieve_EVENT = "retrieve event";
    //删
    String Delete_EVENT = "delete event";

    //获取事件源对象
    IListenerable getEventSource();

    //获取事件类型
    String getEventType();
}
