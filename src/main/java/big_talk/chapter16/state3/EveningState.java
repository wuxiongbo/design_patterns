package big_talk.chapter16.state3;

public class EveningState extends State {
    public void writeProgram(Work w)
    {
        if (w.getWorkFinished())  
        {
            w.setState(new RestState());
            w.writeProgram();
        }
        else
        {
            if (w.getHour() < 21) {
               System.out.println("当前时间："+ w.getHour() +"点 加班哦，疲累之极");
            }
            else {
              w.setState(new SleepingState());
              w.writeProgram();
            }
        }
    }
}

//睡眠状态

