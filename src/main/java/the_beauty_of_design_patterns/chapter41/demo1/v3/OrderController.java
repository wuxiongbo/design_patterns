package the_beauty_of_design_patterns.chapter41.demo1.v3;

import the_beauty_of_design_patterns.chapter41.dependence.OrderVo;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class OrderController {
    public void create(OrderVo order) {
        // ...省略业务逻辑代码...
        Logger.getInstance().log("Created a order: " + order.toString());
    }
}
