package chapter22.demo1.v1;

import chapter22.demo1.v1.util.Html;
import chapter22.demo1.v1.util.HtmlRequest;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class HtmlDownloader {
    //通过构造函数或IOC注入
    private NetworkTransporter transporter;

    public Html downloadHtml(String url) {

//        Byte[] rawHtml = transporter.send(new HtmlRequest(url));

        HtmlRequest htmlRequest = new HtmlRequest(url);
        Byte[] rawHtml = transporter.send(
                htmlRequest.getAddress(), htmlRequest.getContent().getBytes());

        return new Html(rawHtml);
    }
}
