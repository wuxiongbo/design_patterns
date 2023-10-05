package the_beauty_of_design_patterns.chapter42.demo3.v1;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 单例模式替代解决方案 </p>
 *
 * 为了保证全局唯一，除了使用单例，我们还可以用静态方法来实现
 *
 * 静态方法实现方式
 *
 * 不过，静态方法这种实现思路，并不能解决我们之前提到的问题。
 * 实际上，它比单例更加不灵活，比如，它无法支持延迟加载。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/15
 * </pre>
 */


public class IdGenerator {

    private static AtomicLong id = new AtomicLong(0);

    public static long getId() {
        return id.incrementAndGet();
    }


    public static void main(String[] args){

        long id = IdGenerator.getId();

    }

}

