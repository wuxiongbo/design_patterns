package big_talk.chapter20.iterator1;

public class ConcreteIterator extends Iterator{
    private final ConcreteAggregate aggregate;
    private int current = 0;

    //初始化时将具体的聚集对象传入
    public ConcreteIterator(ConcreteAggregate aggregate){
        this.aggregate = aggregate;
    }

    //得到第一个对象
    public Object first(){
        return aggregate.getCurrentItem(0);
    }

    //得到下一个对象
    public Object next() {
        Object ret = null;
        current++;
        if (current < aggregate.getCount()) {
            ret = aggregate.getCurrentItem(current);
        }
        return ret;
    }

    //判断当前是否遍历到结尾，到结尾返回true
    public boolean isDone(){
        return current >= aggregate.getCount() ? true : false;
    }

    //返回当前的聚集对象
    public Object currentItem(){
        return aggregate.getCurrentItem(current);
    }
}

//具体迭代器类(倒序），继承Iterator
