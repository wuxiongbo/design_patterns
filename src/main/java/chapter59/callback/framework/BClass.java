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
     * 方法入参 为 一个 回调对象
     *
     * @param callback 回调对象
     */
    public void process(ICallback callback) {

        //...框架的业务逻辑...
        System.out.println("业务逻辑----1111111----" + " in BClass");


        // 业务逻辑的某一个步骤的实现，进行扩展。
        callback.methodToCallback();
        // 本来是 AClass 调用 BClass 的 process() 函数，现在，BClass 反过来调用 AClass 的 f()函数 。
        // 这就是 回调。

        //...框架的业务逻辑...
        System.out.println("业务逻辑----2222222----" + " in BClass");

    }

}
