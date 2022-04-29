package my_demo.command.demo1.command.concretecommand;

import my_demo.command.demo1.command.Order;
import my_demo.command.demo1.command.receiver.Stock;

/**
 * <p> ConcreteCommand </p>
 *
 * 售货命令
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */

public class SellStock implements Order {

    // 委托人
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

