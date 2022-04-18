package my_demo.friend.v1.friend;

/**
 * <p>  与A 同包 的类，利用A的朋友  绕过限制 调用 被保护的方法</p>
 *
 * D 利用 A的朋友 B ，直接访问了  包权限方法
 *
 * 解决思路：
 *   Friend.func() 只能 B 调用。不能给任何其他类调用。
 *   那么，将对 Friend.func() 的调用逻辑 私有化 即可。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class D {

    public void call(A a) {
        new B().func(a);
    }

}
