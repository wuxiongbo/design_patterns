package chapter59.callback.framework;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public class BClass {

    /**
     * @param callback 回调对象
     */
    public void process(ICallback callback) {
        //...
        callback.methodToCallback();  // BClass 反过来调用 由 AClass 传递过来的 methodToCallback()函数 实现。
        //...
    }

}
