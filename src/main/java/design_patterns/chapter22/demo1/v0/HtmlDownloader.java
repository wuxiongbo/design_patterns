package design_patterns.chapter22.demo1.v0;

import design_patterns.chapter22.demo1.dependence.Html;
import design_patterns.chapter22.demo1.dependence.HtmlRequest;

/**
 * <p>网页下载器</p>
 *
 * 网页下载器 依赖 网络通讯
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

        // 改造前。  返回的请求结果
        Byte[] rawHtml = transporter.send(new HtmlRequest(url));

        return new Html(rawHtml);
    }

}
