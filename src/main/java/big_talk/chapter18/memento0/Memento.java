package big_talk.chapter18.memento0;

import lombok.Getter;

public class Memento {

    @Getter
    private final String state;

    public Memento(String state) {
        this.state = state;
    }
}

//管理者
