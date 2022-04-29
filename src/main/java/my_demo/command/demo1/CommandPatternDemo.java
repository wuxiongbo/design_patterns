package my_demo.command.demo1;

import my_demo.command.demo1.command.concretecommand.BuyStock;
import my_demo.command.demo1.command.concretecommand.SellStock;
import my_demo.command.demo1.invoker.Broker;
import my_demo.command.demo1.command.receiver.Stock;

/**
 * <p>指令模式  命令模式</p>
 *
 *
 * Command：
 *      定义命令的接口，声明执行的方法。
 * ConcreteCommand：
 *      命令接口实现对象，是“虚”的实现；
 *      通常会持有 "接收者" Receiver ，并调用 "接收者" Receiver  的功能来完成命令要执行的操作。
 * Receiver：
 *      接收者，真正执行命令的对象。
 *      任何类都可能成为一个接收者，只要它能够实现命令要求实现的相应功能。
 * Invoker：
 *      要求 "命令对象" 执行请求。通常会持有命令对象，可以持有很多的 "命令对象" 。
 *      这个是客户端真正 触发命令 并  要求命令执行相应操作  的地方，也就是说相当于使用 "命令对象" 的入口。
 * Client：
 *      创建 具体的命令对象，并且设置命令对象的 接收者。
 *      注意，这个不是我们常规意义上的客户端，而是在组装命令对象和接收者，
 *      或许，把这个Client称为 ‘装配者’ 会更好理解，因为真正使用命令的客户端是 从Invoker 来 触发执行。
 *
 *
 * 1. Client创建一个 ConcreteCommand 对象并指定它的 Receiver 对象
 *
 * 2. 某个 Invoker 对象存储该 ConcreteCommand 对象
 *
 * 3. 该 Invoker 通过调用 Command 对象的 Execute操作 来提交一个请求。
 *    若该命令是可撤销的，ConcreteCommand 就在执行Execute操作之前，存储当前状态以用于取消该命令
 *
 * 4. ConcreteCommand 对象 执行该请求，具体执行过程委托给 Receiver
 *
 *
 *  抽象命令                Command
 *                         <>    \
 *                          |     \
 *                         聚合    实现
 *                          |       \
 *  具体命令                 |       ConcreteCommand ———聚合———<> Receiver    接受者
 *                         |
 *                         |
 *  调用者               Invoker
 *
 *
 *  客户端                CommandPattern (Invoker     ConcreteCommand    Receiver)
 *
 *
 *  Receiver 只是 Command(命令) 的函数实现 委托。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class CommandPatternDemo {

    public static void main(String[] args) {


        // Receiver
        Stock abcStock = new Stock();
        // ConcreteCommand  命令
        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);


        // 将 请求命令Command 传递给 Invoker。
        // Invoker ——<> Command(Order)
        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);


        // 使用 Invoker  执行 命令Command
        broker.placeOrders();
    }
}
