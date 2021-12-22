package chapter51.demo3.v2;

/**
 * <p>适配目标</p>
 *
 * 使用适配器模式进行改造
 *
 * 首先，统一接口定义
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public interface ISensitiveWordsFilter {
    String filter(String text);
}
