package refactoring.chapter06.section8.v3;

/**
 * @author bear
 */
public class Gamma {
    private final Account account;
    private final int inputVal;
    private final int quantity;
    private final int yearToDate;


    private int importantValue1;
    private int importantValue2;
    private int importantValue3;


    public Gamma(Account account, int inputVal, int quantity, int yearToDate) {
        this.account = account;
        this.inputVal = inputVal;
        this.quantity = quantity;
        this.yearToDate = yearToDate;
    }

    int compute() {
        importantValue1 = (inputVal * quantity) + account.delta();
        importantValue2 = (inputVal * yearToDate) + 100;

        importantThing2();

        importantThing3();

        return importantValue3 - 2 * importantValue1;
    }

    private void importantThing2() {
        if ((yearToDate - importantValue1) > 100) {
            importantValue2 -= 20;
        }
    }

    private void importantThing3() {
        importantValue3 = importantValue2 * 7;
        // and so on.
    }

}
