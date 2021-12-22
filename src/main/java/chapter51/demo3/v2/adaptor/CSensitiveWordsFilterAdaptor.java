package chapter51.demo3.v2.adaptor;

import chapter51.demo3.component.ASensitiveWordsFilter;
import chapter51.demo3.component.CSensitiveWordsFilter;
import chapter51.demo3.v2.ISensitiveWordsFilter;

/**
 * <p> C适配器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class CSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {

    private CSensitiveWordsFilter cFilter;

    public CSensitiveWordsFilterAdaptor(ASensitiveWordsFilter aFilter) {
        this.cFilter = cFilter;
    }

    @Override
    public String filter(String text) {

        String maskedText = cFilter.filter(text,"***");

        return maskedText;
    }

}
