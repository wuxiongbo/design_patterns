package big_talk_design_patten.chapter28.visitor3;

/**
 * @author Xander Wu
 * @date 2023/12/10 15:27
 */ //人类抽象类
public abstract class Person {
    //接受
    public abstract void accept(Action visitor);
}
