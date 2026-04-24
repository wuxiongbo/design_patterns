package big_talk.chapter27.interpreter1;

public class Note extends Expression {
    public void excute(String key, double value) {
        String note = switch (key) {
            case "C" -> "1";
            case "D" -> "2";
            case "E" -> "3";
            case "F" -> "4";
            case "G" -> "5";
            case "A" -> "6";
            case "B" -> "7";
            default -> "";
        };
        System.out.print(note + " ");
    }
}

//音阶类

