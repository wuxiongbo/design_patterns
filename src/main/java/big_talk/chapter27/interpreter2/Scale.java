package big_talk.chapter27.interpreter2;

public class Scale extends Expression {
    public void excute(String key, double value) {
        String scale = "";
        switch ((int)value) {
            case 1:
                scale = "低音";
                break;
            case 2:
                scale = "中音";
                break;
            case 3:
                scale = "高音";
                break;
        }
        System.out.print(scale+" ");
    }
}

//音速类

