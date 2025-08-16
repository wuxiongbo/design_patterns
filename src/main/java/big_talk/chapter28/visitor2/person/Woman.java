package big_talk.chapter28.visitor2.person;

import java.util.Objects;

//女人
public class Woman extends Person {
    //得到结论或反应
    public void getConclusion() {
        if (Objects.equals(action, "成功")) {
            System.out.println(this.getClass().getSimpleName() + this.action + "时，背后大多有一个不成功的男人。");
        } else if (Objects.equals(action, "失败")) {
            System.out.println(this.getClass().getSimpleName() + this.action + "时，眼泪汪汪，谁也劝不了。");
        } else if (Objects.equals(action, "恋爱")) {
            System.out.println(this.getClass().getSimpleName() + this.action + "时，遇事懂也装作不懂。");
        }
    }
}
