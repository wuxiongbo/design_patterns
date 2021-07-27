package chapter17;

import chapter17.other.Request;
import chapter17.other.Response;

import java.net.http.HttpClient;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Transporter {
    private HttpClient httpClient;

    public Transporter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Response sendRequest(Request request) {

        // ...use httpClient to send request

        return null;
    }
}
