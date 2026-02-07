package design_patterns.chapter51.demo3.v2.adaptor.impl;

import design_patterns.chapter51.demo3.common.ASensitiveWordsFilter;
import design_patterns.chapter51.demo3.v2.adaptor.ISensitiveWordsFilter;

/**
 * <p> A适配器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private final ASensitiveWordsFilter aFilter;

    public ASensitiveWordsFilterAdaptor(ASensitiveWordsFilter aFilter) {
        this.aFilter = aFilter;
    }

    @Override
    public String filter(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        return maskedText;
    }

}