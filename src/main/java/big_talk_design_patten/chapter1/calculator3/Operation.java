package big_talk_design_patten.chapter1.calculator3;

public class Operation {
    public static double getResult(double numberA, 
        double numberB, String operate) {
        double result = 0d;
        switch (operate) {
            case "+":
                result = numberA + numberB;
                break;
            case "-":
                result = numberA - numberB;
                break;
            case "*":
                result = numberA * numberB;
                break;
            case "/":
                result = numberA / numberB;
                break;
            case "pow":
                result= java.lang.Math.pow(numberA,numberB);
                break;
        }
        return result;
    }
}
