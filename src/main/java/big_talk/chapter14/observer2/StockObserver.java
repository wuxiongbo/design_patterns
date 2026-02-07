package big_talk.chapter14.observer2;

class StockObserver extends Observer{
    public StockObserver(String name,Secretary sub){
        super(name,sub);
    }

    public void update(){
        System.out.println(super.sub.name+"："+super.sub.getAction()+"！"+super.name+"，请关闭股票行情，赶紧工作。");
    }
}

//看NBA同事类

