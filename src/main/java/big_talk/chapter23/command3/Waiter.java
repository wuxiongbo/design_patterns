package big_talk.chapter23.command3;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class Waiter{
    private ArrayList<Command> orders = new ArrayList<Command>();

    //设置订单
    public void setOrder(Command command){
        String className=command.getClass().getSimpleName();

        if (className.equals("BakeChickenWingCommand")){
            System.out.println("服务员：鸡翅没有了，请点别的烧烤。");
        }
        else{
            this.orders.add(command);
            System.out.println("增加订单："+className+" 时间："+getNowTime());
        }
    }
    //取消订单
    public void cancelOrder(Command command){
        String className=command.getClass().getSimpleName();
        orders.remove(command);
        System.out.println("取消订单："+className+" 时间："+getNowTime());
    }
    //通知执行
    public void notifyCommand(){
        for(Command command : orders)
            command.excuteCommand();
    }
    private String getNowTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(new Date()).toString();
    }
}


//烤肉串者

