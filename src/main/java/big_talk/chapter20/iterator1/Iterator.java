package big_talk.chapter20.iterator1;

abstract class Iterator{

    public abstract Object first();         //第一个
    public abstract Object next();          //下一个
    public abstract boolean isDone();       //是否到最后
    public abstract Object currentItem();   //当前对象

}

//具体迭代器类，继承Iterator

