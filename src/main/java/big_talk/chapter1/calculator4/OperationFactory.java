package big_talk.chapter1.calculator4;

public class OperationFactory {

    public static Operation createOperate(String operate){
        Operation oper = switch (operate) {
            case "+" -> new Add();
            case "-" -> new Sub();
            case "*" -> new Mul();
            case "/" -> new Div();
            default -> null;
        };
        return oper;
    }
    
}
