package my_demo.friend.v1;

import my_demo.friend.v1.friend.A;

/**
 * <p>冒充A的朋友</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class C extends A.Friend {

    public void call(A a) {
        this.func(a);
    }
}
