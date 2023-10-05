package the_beauty_of_design_patterns.chapter62.demo4;

import the_beauty_of_design_patterns.chapter62.demo4.command.ArrangeFinancing;
import the_beauty_of_design_patterns.chapter62.demo4.command.CloseSale;
import the_beauty_of_design_patterns.chapter62.demo4.command.GetUserInfo;
import the_beauty_of_design_patterns.chapter62.demo4.command.NegotiateSale;
import the_beauty_of_design_patterns.chapter62.demo4.command.TestDriver;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;


/**
 *
 *
 * <a href="https://www.toutiao.com/article/7012619278037991975/?channel=&source=search_tab">
 *     Apache Commons Chain(1) -开源责任链模式（apache commons chain）
 * </a>
 *
 * <a href="https://www.toutiao.com/article/7012620604100608515/?channel=&source=search_tab">
 *     Apache Commons Chain(2) -开源责任链模式（apache commons chain）
 * </a>
 *
 * <a href="https://blog.csdn.net/ProMonkey_chen/article/details/96702524">
 *     Apache Commons Chain 简单介绍
 * </a>
 *
 * @author Xander Wu
 * @date 2022/12/9 13:43
 */
public class CommondChain extends ChainBase {

    public CommondChain() {
        super();
    }




    public static void main(String[] args) {

        // 1）构建 责任链
        CommondChain commondChain = new CommondChain();
        commondChain.addCommand(new GetUserInfo());
        commondChain.addCommand(new TestDriver());
        commondChain.addCommand(new NegotiateSale());
        commondChain.addCommand(new ArrangeFinancing());
        commondChain.addCommand(new CloseSale());


        // 2）初始化 上下文。 用于 保存 链式传递 的数据结果。
        Context context = new ContextBase();


        // 3）触发链式调用
        try {
            commondChain.execute(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}