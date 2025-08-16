package design_patterns.chapter19.demo1.v2.frame;

import design_patterns.chapter19.demo1.v2.UserServiceTest;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>控制反转</p>
 *
 * 控制反转的英文翻译是 Inversion Of Control，缩写为 IOC。
 *
 *
 * 本例是典型的通过框架来实现“控制反转”的例子。
 * 框架提供了一个可扩展的代码骨架，用来组装对象、管理整个执行流程。
 * 程序员利用框架进行开发的时候，只需要往预留的扩展点上，添加跟自己业务相关的代码，就可以利用框架来驱动整个程序流程的执行。
 *
 * 这里的“控制”指的是对程序执行流程的控制，
 * 而“反转”指的是
 *    在没有使用框架之前，“程序员自己” 控制整个程序的执行。
 *    在使用框架之后，整个程序的执行流程可以通过 “框架” 来控制。流程的控制权从 程序员 “反转”到了 框架。
 *
 *
 * 控制反转并不是一种具体的实现技巧，而是一个比较笼统的 “设计思想” ，一般用来指导框架层面的设计。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class JunitApplication {
    private static final List<TestCase> testCases = new ArrayList<>();

    // 将业务的实现，注册进来
    public static void register(TestCase testCase) {
        testCases.add(testCase);
    }


    public static final void main(String[] args) {

        // 注册操作还可以通过配置的方式来实现，不需要程序员显示调用register()
        JunitApplication.register(new UserServiceTest());

        // 轮询 调用多个业务的实现
        for (TestCase case1: testCases) {
            case1.run();
        }

    }
}
