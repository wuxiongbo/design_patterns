package design_patterns.chapter28.v2;

import design_patterns.chapter28.v1.TextTest;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
public class TestCaseRunner {
    public static void main(String[] args) {
        System.out.println("Run testToNumber()");
        new TextTest().testToNumber();

        System.out.println("Run testToNumber_nullorEmpty()");
        new TextTest().testToNumber_nullorEmpty();

        System.out.println("Run testToNumber_containsLeadingAndTrailingSpaces()");
        new TextTest().testToNumber_containsLeadingAndTrailingSpaces();

        System.out.println("Run testToNumber_containsMultiLeadingAndTrailingSpaces()");
        new TextTest().testToNumber_containsMultiLeadingAndTrailingSpaces();

        System.out.println("Run testToNumber_containsInvalidCharaters()");
        new TextTest().testToNumber_containsInvalidCharaters();
    }
}
