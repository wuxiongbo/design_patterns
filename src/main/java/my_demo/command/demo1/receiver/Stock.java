package my_demo.command.demo1.receiver;

/**
 * <p>Receiver</p>
 *
 * Receiver 中实现所有的指令动作
 *
 *
 * 任何类都可能成为一个接收者，只要它能够实现命令要求实现的相应功能。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */


public class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+",Quantity: " + quantity +" ] sold");
    }
}


