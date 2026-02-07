package big_talk.chapter16.state3;

import lombok.Getter;
import lombok.Setter;
public class Work {

    private State current;
    
    public Work(){
        current = new ForenoonState();  
    }
    //设置状态
    public void setState(State value) {
        this.current = value;
    }
    //写代码的状态
    public void writeProgram() {
        this.current.writeProgram(this);
    }

    //当前的钟点
    @Getter
    @Setter
    private int hour;
    //当前工作是否完成
    @Setter
    private boolean workFinished = false;

    // 兼容既有调用方的 getXxx 命名
    public boolean getWorkFinished() {
        return workFinished;
    }
}
