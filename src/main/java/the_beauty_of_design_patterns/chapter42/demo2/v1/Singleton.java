package the_beauty_of_design_patterns.chapter42.demo2.v1;

/**
 * <p> 单例不支持有参数的构造函数 </p>
 *
 * 第一种解决思路是：
 *
 * 创建完实例之后，再调用 init() 函数传递参数。
 * 需要注意的是，我们在使用这个单例类的时候，要先调用 init() 方法，然后才能调用 getInstance() 方法，否则代码会抛出异常。
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

    public static Singleton getInstance() {
        if (instance == null) {
            throw new RuntimeException("Run init() first.");
        }
        return instance;
    }

    public synchronized static Singleton init(int paramA, int paramB) {
        if (instance != null) {
            throw new RuntimeException("Singleton has been created!");
        }
        instance = new Singleton(paramA, paramB);
        return instance;
    }


    public static void main(String[] args){
        Singleton.init(10, 50); // 先init，再使用
        Singleton singleton = Singleton.getInstance();
    }
}
