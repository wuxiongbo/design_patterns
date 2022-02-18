package my_demo.monitor.callback.demo1.callback;

/**
 * <p>回调类</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/13
 * </pre>
 */
public class Callee1 implements ICallBack {
    /**
     * 回调函数
     */
    @Override
    public void callBack() {
        System.out.println("回调对象"+this+"的回调函数回调成功!");
    }
}