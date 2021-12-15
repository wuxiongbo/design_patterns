package chapter42.demo2.v2;

/**
 * <p>单例不支持有参数的构造函数</p>
 *
 * 第二种解决思路是：将参数放到 getInstance() 方法中。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/15
 * </pre>
 */
public class Singleton {
    private static Singleton instance = null;
    private final int paramA;
    private final int paramB;

    private Singleton(int paramA, int paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
    }

    public synchronized static Singleton getInstance(int paramA, int paramB) {
        if (instance == null) {
            instance = new Singleton(paramA, paramB);
        }
        return instance;
    }


    /**
     * 代码实现稍微有点问题。
     * 如果我们如下两次执行 getInstance() 方法，那获取到的 singleton1 和 singleton2 的 paramA 和 paramB 都是 10 和 50。
     * 也就是说，第二次的参数（20，30）没有起作用，而构建的过程也没有给与提示，这样就会误导用户。
     *
     * 这个问题如何解决呢？
     * @param args
     */
    public static void main(String[] args){

        Singleton singleton1 = Singleton.getInstance(10, 50);
        Singleton singleton2 = Singleton.getInstance(20, 30);

    }

}
