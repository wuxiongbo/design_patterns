package design_patterns.chapter51.demo3.v2.adaptor;

/**
 * <p>适配目标</p>
 *
 * <p>
 * 使用适配器模式进行改造
 * <p>
 *
 * 首先，统一接口定义
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public interface ISensitiveWordsFilter {

    /**
     * 统一接口
     * @param text
     * @return
     */
    String filter(String text);

}
