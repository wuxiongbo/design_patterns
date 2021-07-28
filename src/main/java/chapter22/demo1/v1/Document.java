package chapter22.demo1.v1;

import chapter22.demo1.v1.util.Html;

/**
 * <p> 最终的数据--- 文档类 </p>
 *
 * 通过 Document工厂 构建
 *
 * 这个类的问题比较多，主要有三点。
 *      第一，构造函数中的 downloader.downloadHtml() 逻辑复杂，耗时长，不应该放到构造函数中，会影响代码的 可测试性。
 *           注：代码的可测试性  我们后面会讲到，这里你先知道有这回事就可以了。
 *      第二，HtmlDownloader 对象在构造函数中通过 new 来创建，违反了 “基于接口而非实现编程” 的设计思想，也会影响到代码的 可测试性。
 *      第三，从业务含义上来讲，Document 网页文档没必要依赖 HtmlDownloader 类，违背了迪米特法则。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Document {
    private Html html;
    private String url;

//    public Document(String url) {
//        this.url = url;
//        HtmlDownloader downloader = new HtmlDownloader();
//        this.html = downloader.downloadHtml(url);
//    }

    // 改造后如下。
    public Document(String url, Html html) {
        this.html = html;
        this.url = url;
    }


    //...
}
