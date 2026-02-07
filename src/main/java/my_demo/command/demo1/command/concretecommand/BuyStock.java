package my_demo.command.demo1.command.concretecommand;

import my_demo.command.demo1.command.Order;
import my_demo.command.demo1.command.receiver.Stock;

/**
 * <p> ConcreteCommand </p>
 *
 * 购物命令
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */

public class BuyStock implements Order {

    // 委托人
    private final Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        // 将请求 委托给 Receiver
        abcStock.buy();
    }
}

