package big_talk.chapter18.memento0;

import lombok.Getter;
import lombok.Setter;
public class Originator {
    
    //状态
    @Getter
    @Setter
    private String state;
    //显示数据
    public void show(){
        System.out.println("State:"+this.state);
    }

    //创建备忘录
    public Memento createMemento(){
        return new Memento(this.state);
    }

    //恢复备忘录
    public void recoveryMemento(Memento memento){
        this.setState(memento.getState());
    }

}

//备忘录类

