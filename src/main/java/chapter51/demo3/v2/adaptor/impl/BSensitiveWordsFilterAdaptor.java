package chapter51.demo3.v2.adaptor.impl;

import chapter51.demo3.common.BSensitiveWordsFilter;
import chapter51.demo3.v2.adaptor.ISensitiveWordsFilter;

/**
 * <p> Bééå¨</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class BSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private BSensitiveWordsFilter bFilter;

    public BSensitiveWordsFilterAdaptor(BSensitiveWordsFilter bFilter) {
        this.bFilter = bFilter;
    }

    @Override
    public String filter(String text) {

        String maskedText = bFilter.filter(text);

        return maskedText;
    }

}