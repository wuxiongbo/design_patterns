package big_talk_design_patten.chapter28.visitor3;

/**
 * @author Xander Wu
 * @date 2023/12/10 15:28
 */ //男人类
public class Man extends Person {
    public void accept(Action visitor) {
        visitor.getManConclusion(this);
    }
}
