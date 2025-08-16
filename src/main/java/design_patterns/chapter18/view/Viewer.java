package design_patterns.chapter18.view;

import java.util.Map;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public interface Viewer {
    String outputInPlainText();
    Map<String, String> output();
}
