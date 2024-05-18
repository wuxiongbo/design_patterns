package refactoring.chapter06.section7;

/**
 * 值引用
 * @author bear
 * @date 2024/5/17 上午1:24
 * @description
 */
public class ParamValue {

    public static void main(String[] args) {
        int x = 5;
        triple(x);
        System.out.println("x after triple: " + x);
    }

    private static void triple(int arg) {
        arg = arg * 3;
        System.out.println("arg in triple: " + arg);
    }
}
