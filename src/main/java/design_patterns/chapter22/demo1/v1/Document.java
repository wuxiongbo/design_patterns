package design_patterns.chapter22.demo1.v1;

import design_patterns.chapter22.demo1.dependence.Html;

/**
 * <p> 最终的数据--- 文档类 </p>
 *
 * 通过 Document工厂 构建
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Document {
    private final Html html;
    private final String url;

    // 改造前
//    public Document(String url) {
//        this.url = url;
//        HtmlDownloader downloader = new HtmlDownloader();
//        this.html = downloader.downloadHtml(url);
//    }

    // 改造后
    public Document(String url, Html html) {
        this.html = html;
        this.url = url;
    }


    //...
}
