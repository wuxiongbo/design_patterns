package my_demo.command.demo1.concretecommand;

import my_demo.command.demo1.command.Order;
import my_demo.command.demo1.receiver.Stock;

/**
 * <p> ConcreteCommand </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */

public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        // 委托给 Receiver
        abcStock.sell();
    }
}

