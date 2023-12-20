package big_talk_design_patten.chapter1.calculator3;

public class Operation {
    public static double getResult(double numberA, 
        double numberB, String operate) {
        double result = switch (operate) {
            case "+" -> numberA + numberB;
            case "-" -> numberA - numberB;
            case "*" -> numberA * numberB;
            case "/" -> numberA / numberB;
            case "pow" -> Math.pow(numberA, numberB);
            default -> 0d;
        };
        return result;
    }
}
