package my_demo.handlerchain.v1.handle.impl;

import my_demo.handlerchain.v1.handle.ProcessingObject;

/**
 * <p>头数据 处理器 实现</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/24
 * </pre>
 */
public class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return "From Raoul, Mario and Alan: " + input;
    }
}
