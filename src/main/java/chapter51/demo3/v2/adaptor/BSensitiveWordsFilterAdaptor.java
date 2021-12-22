package chapter51.demo3.v2.adaptor;

import chapter51.demo3.component.ASensitiveWordsFilter;
import chapter51.demo3.component.BSensitiveWordsFilter;
import chapter51.demo3.v2.ISensitiveWordsFilter;

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

    public BSensitiveWordsFilterAdaptor(ASensitiveWordsFilter aFilter) {
        this.bFilter = bFilter;
    }

    @Override
    public String filter(String text) {

        String maskedText = bFilter.filter(text);

        return maskedText;
    }

}