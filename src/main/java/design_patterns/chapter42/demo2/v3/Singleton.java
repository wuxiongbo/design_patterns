package design_patterns.chapter42.demo2.v3;

/**
 * <p>单例不支持有参数的构造函数</p>
 *
 * 第三种解决思路是：将参数放到另外一个全局变量中。
 *
 * Config 是一个存储了 paramA 和 paramB 值的全局变量。
 * 里面的值既可以像下面的代码那样通过静态常量来定义，也可以从配置文件中加载得到。
 *
 *
 * 实际上，这种方式是最值得推荐的。
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

    private Singleton() {
        this.paramA = Config.PARAM_A;
        this.paramB = Config.PARAM_B;
    }

    public synchronized static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
