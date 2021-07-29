package chapter17;

import chapter17.dependence.HttpClient;
import chapter17.dependence.Request;
import chapter17.dependence.Response;
import org.apache.commons.lang.StringUtils;


/**
 * <p> 里式替换原则 </p>
 *  父类 Transporter 使用 org.apache.http 库中的 HttpClient 类来传输网络数据。
 *
 *  子类 SecurityTransporter 继承父类 Transporter，增加了额外的功能，支持传输 appId 和 appToken 安全认证信息。
 *
 *
 *  在改造之后的代码中，
 *     如果传递进 demoFunction() 函数的是父类 Transporter 对象，那 demoFunction() 函数并不会有异常抛出，
 *    但如果传递给 demoFunction() 函数的是子类 SecurityTransporter 对象，那 demoFunction() 有可能会有异常抛出。
 *
 *    尽管代码中抛出的是运行时异常（Runtime Exception），我们可以不在代码中显式地捕获处理，
 *    但子类替换父类传递进 demoFunction 函数之后，整个程序的逻辑行为有了改变。
 *
 *  虽然改造之后的代码仍然可以通过 Java 的多态语法，动态地用子类 SecurityTransporter 来替换父类 Transporter，也并不会导致程序编译或者运行报错。
 *  但是，从设计思路上来讲，SecurityTransporter 的设计是不符合 里式替换原则 的。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class SecurityTransporter extends Transporter {

    private String appId;
    private String appToken;

    public SecurityTransporter(HttpClient httpClient, String appId, String appToken) {
        super(httpClient);
        this.appId = appId;
        this.appToken = appToken;
    }

//    @Override
//    public Response sendRequest(Request request) {

//        if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appToken)) {
//            request.addPayload("app-id", appId);
//            request.addPayload("app-token", appToken);
//        }

//        return super.sendRequest(request);

//    }

    @Override
    public Response sendRequest(Request request) {

        if (StringUtils.isBlank(appId) || StringUtils.isBlank(appToken)) {
//            throw new NoAuthorizationRuntimeException(/*...*/);
        }
        request.addPayload("app-id", appId);
        request.addPayload("app-token", appToken);

        return super.sendRequest(request);
    }
}