package chapter62.demo2.handlerchain;

import chapter62.demo2.handler.SensitiveWordFilter;
import chapter62.depencence.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class SensitiveWordFilterChain {

    private List<SensitiveWordFilter> filters = new ArrayList<>();

    public void addFilter(SensitiveWordFilter filter) {
        this.filters.add(filter);
    }

    // return true if content doesn't contain sensitive words.
    public boolean filter(Content content) {

        for (SensitiveWordFilter filter : filters) {

            if (!filter.doFilter(content)) {
                return false;

            }
        }

        return true;
    }
}
