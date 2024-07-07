package my_demo.friend.v2.friend;

/**
 * <p>内部类</p>
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class A {

    // 假设，A 的 func()方法 只想给B调用。
    private void func(){
        System.out.println("A.func()");
    }

    // 内部类 B ，相当于友元。
    // 可选择性的对外暴露私有成员
    public static class B {

        public void call(A a){
            a.func();
        }

    }

}
