package chapter29.demo2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
public class RangeLimiter {

    //全局变量是一种面向过程的编程风格，有种种弊端。
    // 滥用全局变量 也会让编写单元测试变得困难。
    private static AtomicInteger position = new AtomicInteger(0);


    public static final int MAX_LIMIT = 5;
    public static final int MIN_LIMIT = -5;


    public boolean move(int delta) {
        int currentPos = position.addAndGet(delta);
        boolean betweenRange = (currentPos <= MAX_LIMIT) && (currentPos >= MIN_LIMIT);
        return betweenRange;
    }
}
