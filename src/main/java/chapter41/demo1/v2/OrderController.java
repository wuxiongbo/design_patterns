package chapter41.demo1.v2;

import chapter41.dependence.OrderVo;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class OrderController {

    private Logger logger = new Logger();


    public void create(OrderVo order) {
        // ...省略业务逻辑代码...
        logger.log("Created an order: " + order.toString());
    }
}
