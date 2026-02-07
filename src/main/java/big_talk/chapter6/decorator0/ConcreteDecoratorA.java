package big_talk.chapter6.decorator0;

public class ConcreteDecoratorA extends Decorator {
    
    private String addedState;//本类独有子段，以区别于ConcreteDecoratorB类

    public void Operation() {
        super.Operation();//首先运行了原有Component的Operation()

        this.addedState = "具体装饰对象A的独有操作";//再执行本类独有功能
        System.out.println(this.addedState);

    }
}

//ConcreteDecoratorB类

