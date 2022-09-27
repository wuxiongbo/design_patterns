package chapter62.demo2.handler.concrete;

import chapter62.demo2.handler.SensitiveWordFilter;
import chapter62.depencence.Content;

/**
 * <p>广告关键字过滤器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class AdsWordFilter  implements SensitiveWordFilter {

    @Override
    public boolean doFilter(Content content) {

        // 判断内容是否合法
        boolean legal = true;

        //...

        return legal;
    }

}