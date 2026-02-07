package big_talk.chapter20.iterator1;

import java.util.ArrayList;

public class ConcreteAggregate extends Aggregate{

    //声明一个ArrayList泛型变量，用于存放聚合对象
    private ArrayList<Object> items = new ArrayList<Object>();
    public Iterator createIterator(){
        return new ConcreteIterator(this);
    }

    //返回聚集总个数
    public int getCount(){
        return items.size();
    }

    //增加新对象
    public void add(Object object){
        items.add(object);
    }
    
    //得到指定索引对象
    public Object getCurrentItem(int index){
        return items.get(index);
    }

}

//迭代器抽象类

