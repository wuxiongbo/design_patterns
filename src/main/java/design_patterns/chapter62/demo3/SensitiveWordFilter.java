package design_patterns.chapter62.demo3;

/**
 * @author Xander Wu
 * @date 2022/12/19 15:27
 */

import design_patterns.chapter62.depencence.Content;

/**
 * <p> 普通实现 </p>
 *
 * 这里列出 普通实现，与 责任链模式 进行对比，目的是 为了总结 责任链模式的优点：
 *
 * 1)职责链模式 降低代码复杂性
 *   将  大块代码逻辑  拆分成 多个小函数，
 *   将  大类  拆分成  多个小类，
 *   这是应对 代码复杂性 的常用简化思路。
 *
 * 2)职责链模式 让代码满足 开闭原则，提高代码的扩展性。
 *     比如，
 *          我们还需要过滤特殊符号，按照非职责链模式的代码实现方式，我们需要修改 SensitiveWordFilter 的代码，违反 开闭原则。
 *          不过，这样的修改还算比较集中，也是可以接受的。
 *          而 职责链模式的实现方式更加优雅，只需要新添加一个 Filter 类，并且通过 addFilter() 函数将它添加到 FilterChain 中即可，
 *          其他代码完全不需要修改。
 *
 *   不过，你可能会说，即便使用职责链模式来实现，当添加新的过滤算法的时候，还是要修改客户端代码（ApplicationDemo），这样做也没有完全符合 开闭原则。
 *   实际上，细化一下的话，我们可以把上面的代码分成两类：‘框架代码’  和  ‘客户端代码’。
 *   其中，ApplicationDemo 属于  ‘客户端代码’，也就是 使用“框架”的代码。
 *   除 ApplicationDemo 之外的代码，属于  敏感词过滤‘框架代码’ 。
 *
 *
 * 3)职责链模式 使代码更加灵活
 *     相对于 不用职责链的实现方式，配置过滤算法更加灵活，可以选择性的使用某几个过滤算法。
 *
 *
 * 拆分、封装、增加
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class SensitiveWordFilter {

    // return true if content doesn't contain sensitive words.
    public boolean filter(Content content) {

        if (!filterSexyWord(content)) {
            return false;
        }

        if (!filterAdsWord(content)) {
            return false;
        }

        if (!filterPoliticalWord(content)) {
            return false;
        }

        return true;
    }




    private boolean filterSexyWord(Content content) {
        //....
        return false;
    }

    private boolean filterAdsWord(Content content) {
        //...
        return false;
    }

    private boolean filterPoliticalWord(Content content) {
        //...
        return false;
    }

}
