package the_beauty_of_design_patterns.chapter59.callback;

import the_beauty_of_design_patterns.chapter59.callback.framework.BClass;
import the_beauty_of_design_patterns.chapter59.callback.framework.ICallback;

/**
 * 被回调方
 *
 * <p>  '回调函数' 由 '被回调方' 实现，但 该函数 借用‘内部类’包裹并传递。‘内部类’ 起过桥作用</p>
 * <p>
 * <p>
 * <p>
 * 回调函数：
 * AClass.$匿名类.methodToCallback(); （回调函数）
 * methodToCallback() 的真实 实现，委托给了 AClass.f();
 * <p>
 * <p>
 * 回调机制（包括：回调函数、回调对象）
 * <p>
 * 相对于 “普通的函数调用” 来说，“回调” 是一种  ‘双向调用’  关系。
 * 1） A 类 事先 将 ‘函数 f()’ -------注册-------->   到 B 类，
 * 2） 当 A 类 在未来某个时刻，调用 B 类 的 函数 p() 时，
 * B 类 的 函数 p() 内部   -------反过来调用------>  A 类（注册给B类）的 ‘函数 f()’ 。
 * <p>
 * 这里的 ‘函数 f()’ 就是 “回调函数”。
 * <p>
 * A -----调用----------> B
 * B -----反过来调用-----> A
 * 这种 ‘调用机制’ 就叫作 “回调”。
 * <p>
 * <p>
 * <p>
 * <p>
 * A 类 如何将 ‘回调函数’ 传递 给 B 类 呢？ 不同的编程语言，有不同的实现方法。
 * C 语言     可以使用 函数指针，
 * Java 语言  则需要使用 包裹了‘回调函数’的“匿名类对象” ，我们简称为 “回调对象” 。
 * <p>
 * <p>
 * 以 Java 语言举例。这也是 Java 语言中 ‘回调’ 的典型代码实现。代码如下：
 * 从代码实现中，可以看出，‘回调’ 跟 ‘模板模式’  一样，也具有 '复用' 和 '扩展' 的功能。
 * 除了 ‘回调函数’ 之外，BClass 类的 process() 函数中的逻辑都可以复用。
 * 如果
 * ICallback、BClass 类是  框架代码，
 * AClass 是使用框架的      客户端代码，
 * 框架代码是不可修改的，而我们可以通过 ICallback 定制 process() 函数，也就是说，框架 因此具有了扩展的能力。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public class AClass {


    //  回调函数的实现
    public void f() {
        System.out.println("Call back me." + " in AClass");
    }


    public void processA() {
        System.out.println("方法开始" + " in AClass");


        BClass b = new BClass();

        // 由 AClass 调用 B类 的 process()函数
        b.process(
                /*
                  A类 构建  匿名的 ‘回调对象’  (回调对象， 即 专门用来 包裹 ‘回调函数’ 的 对象。)
                  将 ‘回调函数’ f()  传递 给 B类，
                  "回调" ，本质上，是利用了 ‘内部类’的语法机制 实现的
                      （即，内部类 拥有 外部类 所有的 成员属性 与 方法，包括私有的）
                 */
                new ICallback() {

                    // jvm编译后，会隐性的在这里添加一个属性，指向外部类。
                    // 所以，这里的匿名类，起‘过桥’作用。 类似“桥接模式” 的思想

                    // 这里的 methodToCallback 函数， 就是所谓的 “回调函数”。
                    @Override
                    public void methodToCallback() {
                        AClass.this.f();  // 使用“桥接模式”，将 回调方法的实现，委托给了 AClass的 f() 函数。
                    }
                }

        );


        // 要等待 '回调函数' 执行完毕，才能走到这里。 所以，是  “同步回调”
        System.out.println("方法结束" + " in AClass");

    }


    public static void main(String[] args) {

        AClass aClass = new AClass();
        aClass.processA();

    }

}
