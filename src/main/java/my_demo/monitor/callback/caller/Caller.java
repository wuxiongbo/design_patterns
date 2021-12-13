package my_demo.monitor.callback.caller;

import my_demo.monitor.callback.callback.ICallBack;

/**
 * <p>回调者(即，用于‘调用回调函数’ 的类)</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Caller {
    /**
     *
      * @param callBack 回调接口
     */
    public void call(ICallBack callBack) {
        System.out.println("Start...");

        // 执行回调函数
        callBack.callBack();

        System.out.println("End...");
    }
}
