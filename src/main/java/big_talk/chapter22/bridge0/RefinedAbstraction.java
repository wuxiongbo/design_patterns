package big_talk.chapter22.bridge0;

public class RefinedAbstraction extends Abstraction{
    public void operation(){
        System.out.print("具体的Abstraction");
        implementor.operation();
    }
}

