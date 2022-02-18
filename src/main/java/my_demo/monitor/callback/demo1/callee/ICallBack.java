package my_demo.monitor.callback.demo1.callee;

/**
 * <p>回调接口</p>
 * 所谓的 “回调” ，指 用于回调的函数。
 *
 * ‘回调函数’ 只是一个功能片段，由 用户 按照 回调函数 调用约定来实现的一个函数。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface ICallBack {
    /**
     * 回调函数 应该属于观察者模式的一种，目的是为了替代轮询机制，将组件之间的耦合性降低.
     */
    void callBack();
}
