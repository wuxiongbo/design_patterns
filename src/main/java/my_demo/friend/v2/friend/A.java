package my_demo.friend.v2.friend;

/**
 * <p>描述类的信息</p>
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class A {

    private void func(){
        System.out.println("A.func()");
    }


    // 内部类 B ，相当于友元。 可选择性的对外暴露私有成员
    public static class B {
        public void call(A a){
            a.func();
        }
    }

}
