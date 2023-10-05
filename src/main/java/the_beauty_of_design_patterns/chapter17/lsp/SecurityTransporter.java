package the_beauty_of_design_patterns.chapter17.lsp;

import the_beauty_of_design_patterns.chapter17.dependence.HttpClient;
import the_beauty_of_design_patterns.chapter17.dependence.NoAuthorizationRuntimeException;
import the_beauty_of_design_patterns.chapter17.dependence.Request;
import the_beauty_of_design_patterns.chapter17.dependence.Response;
import org.apache.commons.lang.StringUtils;


/**
 * <p> 传输器 </p>
 *
 * 违背  里式替换原则  示例：
 *
 *  父类 Transporter 使用 org.apache.http 库中的 HttpClient 类来传输网络数据。
 *  子类 SecurityTransporter 继承父类 Transporter，增加了额外的功能，支持传输 appId 和 appToken  安全认证信息。
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

    // 改造前
//    @Override
//    public Response sendRequest(Request request) {

//        if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appToken)) {
//            request.addPayload("app-id", appId);
//            request.addPayload("app-token", appToken);
//        }

//        return super.sendRequest(request);

//    }


    // 改造后，违背LSP
    @Override
    public Response sendRequest(Request request) {

        if (StringUtils.isBlank(appId) || StringUtils.isBlank(appToken)) {
            // ‘运行时’ 异常
            throw new NoAuthorizationRuntimeException(/*...*/);
        }
        request.addPayload("app-id", appId);
        request.addPayload("app-token", appToken);

        return super.sendRequest(request);
    }
}