package big_talk.chapter14.observer2;

import java.util.ArrayList;

class NBAObserver extends Observer{
    public NBAObserver(String name,Secretary sub){
        super(name,sub);
    }

    public void update(){
        System.out.println(super.sub.name+"："+super.sub.getAction()+"！"+super.name+"，请关闭NBA直播，赶紧工作。");
    }
}

