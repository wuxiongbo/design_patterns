package big_talk.chapter0.animal0;

import lombok.Getter;
import lombok.Setter;

public class Cat {

    private String name = "";

    public Cat(String name) {
        this.name = name;
    }

    public Cat() {
        this.name = "无名";
    }

    @Getter
    @Setter
    private int shoutNum = 3;

    public String shout() {
        return " 我的名字叫" + name + " " + "喵 ".repeat(Math.max(0, this.shoutNum));
    }
}