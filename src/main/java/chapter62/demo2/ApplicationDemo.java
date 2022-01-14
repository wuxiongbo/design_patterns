package chapter62.demo2;

import chapter62.demo2.handler.concrete.AdsWordFilter;
import chapter62.demo2.handler.concrete.PoliticalWordFilter;
import chapter62.demo2.handler.concrete.SexyWordFilter;
import chapter62.demo2.handlerchain.SensitiveWordFilterChain;
import chapter62.depencence.Content;

/**
 * <p>客户端</p>
 *
 * 对于支持 UGC（User Generated Content，用户生成内容）的应用（比如论坛）来说，
 * 用户生成的内容（比如，在论坛中发表的帖子）可能会包含一些敏感词（比如涉黄、广告、反动等词汇）。
 *
 * 针对这个应用场景，我们就可以利用职责链模式来过滤这些敏感词。
 * 对于包含敏感词的内容，我们有两种处理方式，
 *      第一种是
 *          直接禁止发布，
 *      第二种是
 *          给敏感词打马赛克（比如，用 *** 替换敏感词）之后再发布。这种处理方式符合 GoF 给出的职责链模式的定义，
 *          这种处理方式是 职责链模式 的变体。
 *
 *
 *
 * 我们这里只给出 第一种实现方式的代码示例，如下所示，并且，我们只给出了代码实现的骨架，
 * 具体的敏感词过滤算法，可以参看《数据结构与算法之美》中 多模式字符串匹配 的相关章节自行实现。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class ApplicationDemo {

    public static void main(String[] args) {

        SensitiveWordFilterChain filterChain = new SensitiveWordFilterChain();

        // 扩展点。 处理器的创建 交给客户端
        filterChain.addFilter(new AdsWordFilter());
        filterChain.addFilter(new SexyWordFilter());
        filterChain.addFilter(new PoliticalWordFilter());


        //
        boolean legal = filterChain.filter(new Content());


        if (!legal) {
            // 不发表
        } else {
            // 发表
        }

    }
}
