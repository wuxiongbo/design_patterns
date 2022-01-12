package chapter59.callback;

import chapter59.callback.framework.BClass;
import chapter59.callback.framework.ICallback;

/**
 * <p>回调函数、回调机制、回调对象</p>
 *
 *
 * 相对于普通的函数调用来说，回调是一种双向调用关系。
 *
 * A 类 事先 将 ‘函数 F()’  ----注册---->   到 B 类，
 *
 * A 类 在调用 B 类 的 函数P() 的时候，
 *
 * B 类 的 函数P()  -----反过来调用---->  A 类 注册给 B类 的 ‘函数 F()’ 。
 *
 * 这里的 ‘函数F()’ 就是 “回调函数”。
 *
 * A 调用 B， B 反过来又调用 A， 这种 ‘调用机制’ 就叫作 “回调”。
 *
 *
 *
 * A 类 如何将 ‘回调函数’ 传递 给 B 类 呢？ 不同的编程语言，有不同的实现方法。
 *   C 语言     可以使用函数指针，
 *   Java 语言  则需要使用 包裹了‘回调函数’的“类对象” ，我们简称为 “回调对象” 。
 *
 *
 * 用 Java 语言举例说明。
 * 这是 Java 语言中 ‘回调’ 的典型代码实现。代码如下
 *
 * 从代码实现中，我们可以看出，‘回调’ 跟 ‘模板模式’  一样，也具有 '复用' 和 '扩展' 的功能。
 * 除了 ‘回调函数’ 之外，BClass 类的 process() 函数中的逻辑都可以复用。
 *   如果
 *       ICallback、BClass 类是  框架代码，
 *       AClass 是使用框架的      客户端代码，
 *   框架代码是不可修改的，而我们可以通过 ICallback 定制 process() 函数，也就是说，框架 因此具有了扩展的能力。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public class AClass {


    public static void main(String[] args) {

        BClass b = new BClass();

        // 由AClass 调用 B类的函数
        b.process(

                // 由A类构建 回调对象 并 传递给 B类，回调对象 是用来包裹回调函数的。
                new ICallback() {
                    @Override
                    public void methodToCallback() {
                        System.out.println("Call back me.");
                    }
                }

        );


    }



}
