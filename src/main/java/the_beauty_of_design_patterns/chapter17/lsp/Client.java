package the_beauty_of_design_patterns.chapter17.lsp;

import the_beauty_of_design_patterns.chapter17.dependence.Request;
import the_beauty_of_design_patterns.chapter17.dependence.Response;

/**
 * <p>描述类的信息</p>
 *
 *
 * 在改造之后的代码中，
 *   如果传递进 demoFunction() 函数的是父类 Transporter 对象，那 demoFunction() 函数并不会有异常抛出，
 *   但如果传递给 demoFunction() 函数的是子类 SecurityTransporter 对象，那 demoFunction() 有可能会有异常抛出。
 *
 *   尽管代码中抛出的是 ‘运行时’异常（Runtime Exception），我们可以不在代码中显式地捕获处理，
 *   但子类替换父类传递进 demoFunction 函数之后，整个程序的逻辑行为有了改变。
 *
 * 虽然改造之后的代码仍然可以通过 Java 的多态语法，动态地用 子类 SecurityTransporter 来替换  父类 Transporter，
 * 改造之后也并不会导致 程序 ‘编译’报错 或 ‘运行’报错。
 * 但是，从设计思路上来讲，SecurityTransporter 的设计是不符合 “里式替换原则” 的。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Client {


    /**
     * @param transporter 可能是  父类 Transporter本身，也可能是子类 SecurityTransporter
     */
    public void demoFunction(Transporter transporter) {

        Request request = new Request();
        //...省略设置request中数据值的代码...


        Response response = transporter.sendRequest(request);

        //...省略其他逻辑...
    }


}
