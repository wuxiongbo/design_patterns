package design_patterns.chapter51.demo3.v1;

import design_patterns.chapter51.demo3.common.ASensitiveWordsFilter;
import design_patterns.chapter51.demo3.common.BSensitiveWordsFilter;
import design_patterns.chapter51.demo3.common.CSensitiveWordsFilter;

/**
 * <p>统一接口设计</p>
 *
 * 未使用适配器模式之前的代码：
 *      代码的可测试性、扩展性不好
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class RiskManagement {

    private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
    private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();
    private CSensitiveWordsFilter cFilter = new CSensitiveWordsFilter();

    //  代码的 可测试性、扩展性  不好
    public String filterSensitiveWords(String text) {

        // 接口不统一
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        maskedText = bFilter.filter(maskedText);
        maskedText = cFilter.filter(maskedText, "***");

        return maskedText;
    }
}
