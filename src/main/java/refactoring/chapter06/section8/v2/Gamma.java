package refactoring.chapter06.section8.v2;

public class Gamma {
    private final Account _account;
    private final int inputVal;
    private final int quantity;
    private final int yearToDate;



    private int importantValue1;
    private int importantValue2;
    private int importantValue3;



    public Gamma(Account _account, int inputVal, int quantity, int yearToDate) {
        this._account = _account;
        this.inputVal = inputVal;
        this.quantity = quantity;
        this.yearToDate = yearToDate;
    }


    int compute() {
        importantValue1 = (inputVal * quantity) + _account.delta();
        importantValue2 = (inputVal * yearToDate) + 100;

        if ((yearToDate - importantValue1) > 100) {
            importantValue2 -= 20;
        }

        importantValue3 = importantValue2 * 7;

        // and so on.
        return importantValue3 - 2 * importantValue1;
    }
}
