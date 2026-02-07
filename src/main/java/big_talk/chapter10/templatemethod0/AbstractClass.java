package big_talk.chapter10.templatemethod0;

public abstract class AbstractClass {
    //模板方法
    public void templateMethod() {
        
        //写一些可以被子类共享的代码

        this.primitiveOperation1();
        this.primitiveOperation2();
    }
    
    public abstract void primitiveOperation1(); //子类个性的行为，放到子类去实现
    public abstract void primitiveOperation2(); //子类个性的行为，放到子类去实现

}

//模板方法具体类A

