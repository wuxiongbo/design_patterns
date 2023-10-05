package the_beauty_of_design_patterns.chapter22.demo1.v1;

import the_beauty_of_design_patterns.chapter22.demo1.dependence.Html;
import the_beauty_of_design_patterns.chapter22.demo1.dependence.HtmlRequest;

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

        // 改造前
        //      返回的请求结果
//        Byte[] rawHtml = transporter.send(new HtmlRequest(url));


        // 改造后。
        HtmlRequest htmlRequest = new HtmlRequest(url);
        String address = htmlRequest.getAddress();
        Byte[] content = htmlRequest.getContent().getBytes();

        // NetworkTransporter网络通讯器  不应该依赖 HtmlRequest， 应该尽可能通用，而不只是html下载
        //      返回的请求结果
        Byte[] rawHtml = transporter.send( address, content);

        return new Html(rawHtml);
    }

}
