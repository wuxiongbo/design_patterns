package my_demo.monitor.callback.demo1.callee;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/13
 * </pre>
 */
public class CallBack implements ICallBack {
    @Override
    public void callBack() {
        System.out.println("回调函数回调成功!");
    }
}