package my_demo.friend.v1.friend;

/**
 * <p>描述类的信息</p>
 *
 * 由于A.func为private,故它只能被A.Friend.func(A)访问，
 * 又A.Friend.func(A)为protected，于是它只能被A.Friend及其子类访问。
 *
 * 由Friend的构造方法可知，任何不是B的A子类都不能实例化。
 *
 * 又通过B的final防止了B的子类通过static方法中调用newB().func(a)对A.func的访问。
 *
 * 于是A.func只能被类B访问
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

    public static abstract class Friend{

        // 指定朋友
        private static final Class<? extends Friend> friend = B.class;

        public Friend(){
            // 判断是不是朋友
            if(this.getClass()!= friend){
                throw new UnsupportedOperationException();
            }
        }

        // 朋友才可以直接 调用私有方法
        protected void func(A a){
            a.func();
        }
    }

}
