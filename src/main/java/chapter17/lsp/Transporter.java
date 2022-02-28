package chapter17.lsp;

import chapter17.dependence.HttpClient;
import chapter17.dependence.Request;
import chapter17.dependence.Response;


/**
 * <p>传输器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Transporter {
    // 依赖注入
    private HttpClient httpClient;
    public Transporter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public Response sendRequest(Request request) {

        // ...use httpClient to send request

        return null;
    }
}
