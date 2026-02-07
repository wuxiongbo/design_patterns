package design_patterns.chapter51.demo3.v2.adaptor.impl;

import design_patterns.chapter51.demo3.common.CSensitiveWordsFilter;
import design_patterns.chapter51.demo3.v2.adaptor.ISensitiveWordsFilter;

/**
 * <p> C适配器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class CSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private final CSensitiveWordsFilter cFilter;

    public CSensitiveWordsFilterAdaptor(CSensitiveWordsFilter cFilter) {
        this.cFilter = cFilter;
    }

    @Override
    public String filter(String text) {

        String maskedText = cFilter.filter(text,"***");

        return maskedText;
    }

}
