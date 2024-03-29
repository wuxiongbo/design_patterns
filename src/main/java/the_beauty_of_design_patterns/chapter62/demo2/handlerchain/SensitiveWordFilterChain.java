package the_beauty_of_design_patterns.chapter62.demo2.handlerchain;

import the_beauty_of_design_patterns.chapter62.demo2.handler.SensitiveWordFilter;
import the_beauty_of_design_patterns.chapter62.depencence.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>敏感词过滤器链</p>
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

            // 判断内容是否合法
            if (!filter.doFilter(content)) {

                // 只要有处理器检测不通过，则 内容非法
                // 具体为，返回false，不再向下传递请求。
                return false;

            }

        }


        // 通过所有检测，则 内容合法，返回 true。
        return true;
    }
}
