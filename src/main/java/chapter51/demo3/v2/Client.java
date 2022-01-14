package chapter51.demo3.v2;

import chapter51.demo3.common.ASensitiveWordsFilter;
import chapter51.demo3.common.BSensitiveWordsFilter;
import chapter51.demo3.common.CSensitiveWordsFilter;
import chapter51.demo3.v2.adaptor.impl.ASensitiveWordsFilterAdaptor;
import chapter51.demo3.v2.adaptor.impl.BSensitiveWordsFilterAdaptor;
import chapter51.demo3.v2.adaptor.impl.CSensitiveWordsFilterAdaptor;

/**
 * <p>统一接口设计</p>
 *
 * 使用适配器模式后， 扩展性更好，更加符合 ‘开闭原则’ ，
 *
 * 如果添加一个新的敏感词过滤系统，这个 RiskManagement类 完全不需要改动；
 * 而且，基于 ‘接口’ 而非 ‘实现’ 编程，代码的可测试性更好。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class Client {
    /**
     * 将接口不统一的 a b c系统，通过 适配器 适配为统一接口
     * @param args
     */
    public static void main(String[] args){

        RiskManagement riskManagement = new RiskManagement();
        riskManagement.addSensitiveWordsFilter(new ASensitiveWordsFilterAdaptor(new ASensitiveWordsFilter()));
        riskManagement.addSensitiveWordsFilter(new BSensitiveWordsFilterAdaptor(new BSensitiveWordsFilter()));
        riskManagement.addSensitiveWordsFilter(new CSensitiveWordsFilterAdaptor(new CSensitiveWordsFilter()));


        String text = "这是待和谐文本这是待和谐文本这是待和谐文本";

        // 和谐后的文本
        String maskedText = riskManagement.filterSensitiveWords(text);

    }
}
