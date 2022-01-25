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

        //...框架的业务逻辑...


        // 业务逻辑的某一个步骤的实现，进行扩展。
        callback.methodToCallback();  // BClass 反过来调用 由 AClass 传递过来的 f()函数 。


        //...框架的业务逻辑...

    }

}
