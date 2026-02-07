package big_talk.chapter20.iterator1;

public class ConcreteIteratorDesc extends Iterator{
    private ConcreteAggregate aggregate;
    private int current = 0;

    public ConcreteIteratorDesc(ConcreteAggregate aggregate){
        this.aggregate = aggregate;
        current = aggregate.getCount()-1;
    }

    //第一个对象
    public Object first(){
        return aggregate.getCurrentItem(aggregate.getCount()-1);
    }

    //下一个对象
    public Object next() {
        Object ret = null;
        current--;
        if (current >= 0) {
            ret = aggregate.getCurrentItem(current);
        }
        return ret;
    }

    //判断当前是否遍历到结尾，到结尾返回true
    public boolean isDone(){
        return current <0 ? true : false;
    }

    //返回当前的聚集对象
    public Object currentItem(){
        return aggregate.getCurrentItem(current);
    }
}

