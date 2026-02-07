package big_talk.chapter14.observer2;

public class NBAObserver extends Observer{
    public NBAObserver(String name,Secretary sub){
        super(name,sub);
    }

    public void update(){
        System.out.println(super.sub.name+"："+super.sub.getAction()+"！"+super.name+"，请关闭NBA直播，赶紧工作。");
    }
}

