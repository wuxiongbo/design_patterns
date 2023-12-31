package big_talk_design_patten.chapter28.visitor2.person;

import lombok.Getter;
import lombok.Setter;

//人抽象类
@Setter
@Getter
public abstract class Person {

    protected String action;

    //得到结论或反应
    public abstract void getConclusion();
}
