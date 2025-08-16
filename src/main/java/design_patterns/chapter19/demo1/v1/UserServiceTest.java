package design_patterns.chapter19.demo1.v1;

/**
 * <p>控制反转（IOC） 示例</p>
 *
 * 改造前
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class UserServiceTest {

    // 业务逻辑
    public static boolean doTest() {
        // ...
        return false;
    }

    public static void main(String[] args) {

        // 业务执行流程。 这部分逻辑可以放到框架中
        if (doTest()) {
            System.out.println("Test succeed.");
        } else {
            System.out.println("Test failed.");
        }

    }

}
