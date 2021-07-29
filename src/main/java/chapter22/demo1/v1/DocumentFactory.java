package chapter22.demo1.v1;

import chapter22.demo1.v1.util.Html;

/**
 * <p> Document工厂类 </p>
 *
 * Document工厂类 依赖 网页下载器
 *
 * 通过一个工厂方法来创建 Document
 * 解决  原Document类 中存在的三个问题
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class DocumentFactory {

    private HtmlDownloader downloader;

    public DocumentFactory(HtmlDownloader downloader) {
        this.downloader = downloader;
    }

    public Document createDocument(String url) {
        Html html = downloader.downloadHtml(url);
        return new Document(url, html);
    }
}