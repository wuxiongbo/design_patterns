package the_beauty_of_design_patterns.chapter51.demo3.v2;

import the_beauty_of_design_patterns.chapter51.demo3.v2.adaptor.ISensitiveWordsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>统一接口设计</p>
 *
 * 这部分实现， 类似责任链模式的变体。
 * @see the_beauty_of_design_patterns.chapter62.demo1.v4.Application
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


        // 使用 职责链模式的变体 对代码进行重构。
        // 此处，请求 会轮流被  所有的处理器  处理一遍， 不存在中途终止的情况。
        for (ISensitiveWordsFilter filter : filters) {
            maskedText = filter.filter(maskedText);
        }


        return maskedText;
    }

}
