package big_talk.chapter23.command2;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println();       

        //开店前的准备
        Barbecuer boy = new Barbecuer();//烤肉厨师
        Command bakeMuttonCommand1 = new BakeMuttonCommand(boy);            //烤羊肉串
        Command bakeChickenWingCommand1 = new BakeChickenWingCommand(boy);  //烤鸡翅
        Waiter girl = new Waiter();     //服务员

        //开门营业
        girl.setOrder(bakeMuttonCommand1);      //下单烤羊肉串
        girl.notifyCommand();                   //通知厨师烤肉
        girl.setOrder(bakeMuttonCommand1);      //下单烤羊肉串
        girl.notifyCommand();                   //通知厨师烤肉
        girl.setOrder(bakeChickenWingCommand1); //下单烤鸡翅
        girl.notifyCommand();                   //通知厨师烤肉

        System.out.println();
        System.out.println("**********************************************");
    }
}

//抽象命令类

