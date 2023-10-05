package the_beauty_of_design_patterns.chapter22.demo1.v0;

import the_beauty_of_design_patterns.chapter22.demo1.dependence.Html;

/**
 * <p> 最终的数据--- 文档类 </p>
 *
 * 通过 Document工厂 构建
 *
 * 这个类的问题比较多，主要有三点。
 *      第一，构造函数中的 downloader.downloadHtml() 逻辑复杂，耗时长，不应该放到构造函数中，会影响代码的 可测试性。
 *           注：代码的可测试性  我们后面会讲到，这里你先知道有这回事就可以了。
 *      第二，HtmlDownloader 对象在构造函数中通过 new 来创建，违反了 “基于接口而非实现编程” 的设计思想，也会影响到代码的 可测试性。
 *      第三，从业务含义上来讲，Document 网页文档没必要依赖 HtmlDownloader 类，违背了 “迪米特法则” 。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Document {
    private Html html;
    private String url;

    // 改造前
    public Document(String url) {
        this.url = url;

        // 创建对象的逻辑不应该出现在这里。
        HtmlDownloader downloader = new HtmlDownloader();


        // 网页文档 没必要依赖 下载器。 业务上违背了 “迪米特法则” （松耦合）
        this.html = downloader.downloadHtml(url);
    }

    //...
}
