package the_beauty_of_design_patterns.chapter62.demo2.handler.concrete;

import the_beauty_of_design_patterns.chapter62.demo2.handler.SensitiveWordFilter;
import the_beauty_of_design_patterns.chapter62.depencence.Content;

/**
 * <p>政治敏感词过滤</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class PoliticalWordFilter  implements SensitiveWordFilter {

    @Override
    public boolean doFilter(Content content) {

        // 判断内容是否合法
        boolean legal = true;

        //...

        return legal;
    }

}