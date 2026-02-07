package design_patterns.chapter22.demo1.v1;

import design_patterns.chapter22.demo1.dependence.Html;

/**
 * <p> Document工厂类 </p>
 *
 * Document工厂类 依赖 网页下载器
 *
 * 新增一个 工厂方法类，
 * 用 工厂方法 来创建 Document
 * 解决  原Document类 中存在的三个问题
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class DocumentFactory {

    private final HtmlDownloader downloader;

    public DocumentFactory(HtmlDownloader downloader) {
        this.downloader = downloader;
    }

    public Document createDocument(String url) {

        // 下载器的构建逻辑放在本工厂类
        Html html = downloader.downloadHtml(url);

        //  不用依赖注入 下载器，实现业务解耦
        return new Document(url, html);
    }
}
