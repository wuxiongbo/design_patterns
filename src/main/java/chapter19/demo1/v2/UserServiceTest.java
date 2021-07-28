package chapter19.demo1.v2;

import chapter19.demo1.v2.frame.TestCase;

/**
 * <p>控制反转（IOC） 示例</p>
 *
 * 改造后
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class UserServiceTest extends TestCase {

    // 只负责写业务逻辑
    @Override
    public boolean doTest() {
        // ...
        return false;
    }

    // 执行流程交给框架完成。

}