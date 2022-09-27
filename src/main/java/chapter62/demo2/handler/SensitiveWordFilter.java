package chapter62.demo2.handler;

import chapter62.depencence.Content;

/**
 * <p>敏感词过滤器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/13
 * </pre>
 */
public interface SensitiveWordFilter {

    boolean doFilter(Content content);

}
