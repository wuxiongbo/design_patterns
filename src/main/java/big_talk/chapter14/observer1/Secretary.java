package big_talk.chapter14.observer1;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public class Secretary{
    protected String name;
    public Secretary(String name){
        this.name = name;
    }

    //同事列表
    private ArrayList<StockObserver> list = new ArrayList<StockObserver>();
    @Getter
    @Setter
    private String action;

    //增加同事（有几个同事需要前台通知，就增加几个对象）
    public void attach(StockObserver observer){
        list.add(observer);
    }

    //通知
    public void notifyEmployee(){
        //待老板来了，就给所有登记过的同事发通知
        for(StockObserver item : list){
            item.update();
        }
    }

    //得到状态
    //设置状态（就是设置具体通知的话）
}

//看股票同事类

