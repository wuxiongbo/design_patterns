package big_talk.chapter18.memento0;

import lombok.Getter;
import lombok.Setter;
public class Memento {

    @Getter

    @Setter

    private String state;

    public Memento (String state){
        this.state = state;
    }
}

//管理者

