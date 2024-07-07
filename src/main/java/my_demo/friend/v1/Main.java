package my_demo.friend.v1;

import my_demo.friend.v1.friend.A;
import my_demo.friend.v1.friend.B;
import my_demo.friend.v1.friend.D;

/**
 * <p>描述类的信息</p>
 *
 * 然而，这个实现方法有几个缺陷：
 * 一、JAVA中protected保护的对象并非只能被子类访问，还可以被同一个包中的其他类访问。
 *    于是和 A、B 同在一个包中的 类 D ，就可以通过 new B().func(A) 的方式访问该方法。
 * 二、B 被设为final，导致它不能被继承。
 *    JAVA没有多重继承，B继承了A.Friend，那么它就不能再继承其他任何类。
 *    这导致，B只能作为一个类的友元。即B作了A的友元，就做不了C的友元。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class Main {
    public static void main(String[] args){

        B b = new B();

        b.call(new A());


        C c = new C();

        D d = new D();

    }
}
/*
* 输出：
*
* A.func()
* Exception in thread "main" java.lang.UnsupportedOperationException
*
* */