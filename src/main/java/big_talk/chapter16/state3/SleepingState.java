package big_talk.chapter16.state3;

public class SleepingState extends State {
    public void writeProgram(Work w) {
        System.out.println("当前时间："+ w.getHour() +"点 不行了，睡着了。");
    }
}

//下班休息状态

