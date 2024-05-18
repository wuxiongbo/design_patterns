package refactoring.chapter06.section7;

import java.util.Date;

/**
 * 对象引用
 * @author bear
 * @date 2024/5/17 上午1:28
 * @description
 */
public class ParamReference {
    public static void main(String[] args) {
        Date d1 = new Date("1 Apr 98");
        nextDateUpdate(d1);
        System.out.println("d1 after nextDay: " + d1);

        Date d2 = new Date("1 Apr 98");
        nextDateReplace(d2);
        System.out.println("d2 after nextDay: " + d2);
    }


    private static void nextDateUpdate(Date arg) {
        arg.setDate(arg.getDate() + 1);
        System.out.println("arg in nextDay: " + arg);
    }

    private static void nextDateReplace(Date arg) {
        arg = new Date(arg.getYear(), arg.getMonth(), arg.getDate() + 1);
        System.out.println("arg in nextDay: " + arg);
    }
}

/*
arg in nextDay: Thu Apr 02 00:00:00 CST 1998
d1 after nextDay: Thu Apr 02 00:00:00 CST 1998
arg in nextDay: Thu Apr 02 00:00:00 CST 1998
d2 after nextDay: Wed Apr 01 00:00:00 CST 1998

 */