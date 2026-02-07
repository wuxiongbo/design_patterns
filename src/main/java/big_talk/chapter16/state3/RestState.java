package big_talk.chapter16.state3;

public class RestState extends State {
    public void writeProgram(Work w) {
        System.out.println("当前时间："+ w.getHour() +"点 下班回家了");
    }
}

//工作类

