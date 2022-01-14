package chapter51.demo3.v2;

import chapter51.demo3.v2.adaptor.ISensitiveWordsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>统一接口设计</p>
 *
 * 这部分实现， 类似责任链模式的变体。
 * @see chapter62.demo1.v4.Application
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class RiskManagement {

    // 存放 适配器
    private List<ISensitiveWordsFilter> filters = new ArrayList<>();

    // 添加 适配器
    public void addSensitiveWordsFilter(ISensitiveWordsFilter filter) {
        filters.add(filter);
    }

    // 调用适配器，统一进行过滤操作
    public String filterSensitiveWords(String text) {
        String maskedText = text;

        // 责任链模式 变体，请求 会被  所有的处理器  都处理一遍， 不存在中途终止的情况。
        for (ISensitiveWordsFilter filter : filters) {
            maskedText = filter.filter(maskedText);
        }

        return maskedText;
    }

}
