package the_beauty_of_design_patterns.chapter59.callback;

import the_beauty_of_design_patterns.chapter59.callback.framework.BClass;

/**
 * 被回调方
 *
 * <p>  回调函数 由 被回调方 实现，函数式写法  </p>
 * <p>
 * 回调函数：
 * AClass.$匿名类.methodToCallback();
 * methodToCallback() 的实现，委托给了 AClass.f();
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public class AClass2 {


    public void f() {
        System.out.println("Call back me." + " in AClass");
    }


    public void processA() {
        System.out.println("方法开始" + " in AClass");

        BClass b = new BClass();

        // 在 Java 8 之前，“内部类” 是实现 “闭包” 的唯一方式。
        // 在 Java 8 之后，我们可以使用 lambda表达式  来实现 “闭包” 行为，并且语法更加优雅和简洁，
        b.process(AClass2.this::f);

        System.out.println("方法结束" + " in AClass");
    }


    public static void main(String[] args) {

        AClass2 aClass = new AClass2();
        aClass.processA();

    }

}
