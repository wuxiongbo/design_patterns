package the_beauty_of_design_patterns.chapter59.callback;

import the_beauty_of_design_patterns.chapter59.callback.framework.BClass;
import the_beauty_of_design_patterns.chapter59.callback.framework.ICallback;

/**
 * <p>回调函数  由 被回调方的内部类实现</p>
 *
 *
 * 回调函数：
 *     AClass.$匿名类.methodToCallback();  （回调函数）
 *     methodToCallback()具备完整的实现。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/18
 * </pre>
 */
public class AClass1 {

    public void processA(){
        System.out.println("方法开始" + " in AClass");

        BClass b = new BClass();

        b.process(
            new ICallback() {
                @Override
                public void methodToCallback() {
                    System.out.println("Call back me." + " in AClass");
                }
            }
        );

        // 要等待回调函数执行完，才能走到这里。 所以是  “同步回调”
        System.out.println("方法结束" + " in AClass");

    }



    public static void main(String[] args) {

        AClass1 aClass = new AClass1();
        aClass.processA();

    }

}
