package chapter51.demo3.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>统一多个类的接口设计</p>
 *
 * 扩展性更好，更加符合开闭原则，
 * 如果添加一个新的敏感词过滤系统，这个类完全不需要改动；
 * 而且，基于 接口 而非 实现 编程，代码的可测试性更好。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class RiskManagement {

    private List<ISensitiveWordsFilter> filters = new ArrayList<>();

    public void addSensitiveWordsFilter(ISensitiveWordsFilter filter) {
        filters.add(filter);
    }

    public String filterSensitiveWords(String text) {
        String maskedText = text;
        for (ISensitiveWordsFilter filter : filters) {
            maskedText = filter.filter(maskedText);
        }
        return maskedText;
    }
}
