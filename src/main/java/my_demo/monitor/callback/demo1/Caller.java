package my_demo.monitor.callback.demo1;

/**
 * <p> 调用方(即，用于‘调用回调函数’ 的类)</p>
 *
 * 类似 被观察者 的角色
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Caller {
    /**
     * @param callBack 回调对象
     */
    public void call(ICallBack callBack) {
        System.out.println("Start...");

        // 执行回调函数。  调用 callBack 进行通知。 类似通知 观察者。
        callBack.callBack();


        System.out.println("End...");
    }

}
