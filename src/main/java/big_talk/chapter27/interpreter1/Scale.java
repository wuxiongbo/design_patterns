package big_talk.chapter27.interpreter1;

public class Scale extends Expression {
    public void excute(String key, double value) {
        String scale = switch ((int) value) {
            case 1 -> "低音";
            case 2 -> "中音";
            case 3 -> "高音";
            default -> "";
        };
        System.out.print(scale + " ");
    }
}

