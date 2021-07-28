package chapter19.demo1.v2.frame;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public abstract class TestCase {

    // 业务执行流程。 这部分是通用逻辑，抽取出来。  流程的控制权从 程序员 “反转”到了 框架
    public void run() {
        if (doTest()) {
            System.out.println("Test succeed.");
        } else {
            System.out.println("Test failed.");
        }
    }


    // 框架预留的扩展点。
    // 后续 新增测试单元，只需要在本扩展点填充具体的测试代码就可以实现之前的功能，
    // 完全不需要再写负责执行流程的 main() 函数了。
    public abstract boolean doTest();

}
