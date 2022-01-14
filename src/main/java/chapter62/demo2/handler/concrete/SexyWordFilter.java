package chapter62.demo2.handler.concrete;

import chapter62.demo2.handler.SensitiveWordFilter;
import chapter62.depencence.Content;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public class SexyWordFilter  implements SensitiveWordFilter {
    @Override
    public boolean doFilter(Content content) {

        // 判断内容是否合法
        boolean legal = true;

        //...

        return legal;
    }
}