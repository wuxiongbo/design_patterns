package big_talk.chapter6.decorator0;

public class ConcreteDecoratorB extends Decorator {

    public void Operation() {
        super.Operation();//首先运行了原有Component的Operation()
        this.AddedBehavior();//再执行本类独有功能
    }

    //本类独有方法，以区别于ConcreteDecoratorA类
    private void AddedBehavior() { 
    	System.out.println("具体装饰对象B的独有操作");
    }
}

