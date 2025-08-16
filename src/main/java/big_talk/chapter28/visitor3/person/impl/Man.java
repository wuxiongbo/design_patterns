package big_talk.chapter28.visitor3.person.impl;

import big_talk.chapter28.visitor3.action.Action;
import big_talk.chapter28.visitor3.person.Person;

/**
 * 男人类
 * @author Xander Wu
 * @date 2023/12/10 15:28
 */
public class Man extends Person {
    public void accept(Action visitor) {
        // 第二次分派
        visitor.getManConclusion(this);
    }
}
