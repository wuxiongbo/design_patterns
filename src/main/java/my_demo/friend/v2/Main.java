package my_demo.friend.v2;

import my_demo.friend.v2.friend.A;

/**
 * <p>友元的实现。内部类</p>
 *
 * 内部类
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class Main {

    public static void main(String[] args){

        A.B b = new A.B();

        b.call(new A());

    }
}
/*
* 输出：
*
* A.func()
*
* */