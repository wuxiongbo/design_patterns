package the_beauty_of_design_patterns.chapter42.demo3.v2;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 饿汉式 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);
    private static final IdGenerator INSTANCE = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
