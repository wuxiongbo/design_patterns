package the_beauty_of_design_patterns.chapter51.demo3.v2.adaptor.impl;

import the_beauty_of_design_patterns.chapter51.demo3.common.BSensitiveWordsFilter;
import the_beauty_of_design_patterns.chapter51.demo3.v2.adaptor.ISensitiveWordsFilter;

/**
 * <p> B适配器</p>
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