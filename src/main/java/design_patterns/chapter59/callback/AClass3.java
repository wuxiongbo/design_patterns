package design_patterns.chapter59.callback;

import design_patterns.chapter59.callback.framework.BClass;
import design_patterns.chapter59.callback.framework.ICallback;

/**
 * 被回调方
 * <p> 最简单的方法，回调函数 由 被回调方 实现，直接传入被调用方</p>
 * <p>
 * <p>
 * 回调函数：
 * AClass3.methodToCallback()
 * methodToCallback()具备完整的实现。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/18
 * </pre>
 */
public class AClass3 implements ICallback {

    @Override
    public void methodToCallback() {
        System.out.println("Call back me." + " in AClass");
    }


    // 入口
    public void processA() {
        System.out.println("方法开始" + " in AClass");

        BClass b = new BClass();
        b.process(this);

        System.out.println("方法结束" + " in AClass");
    }


    public static void main(String[] args) {

        AClass3 aClass = new AClass3();
        aClass.processA();
    }
}
