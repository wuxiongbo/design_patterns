package refactoring.chapter06.section8.v2;

/**
 * 1)为了把这个函数变成⼀个函数对象，我⾸先需要声明⼀个新类。
 * 在此新类中, 我应该提供⼀个final字段,⽤以保存源对象;
 * 对于函数的每⼀个参数和每⼀个临时变量，也以⼀个字段逐⼀保存。
 * <p>
 * 2)现在可以把原本的函数搬到compute（）了。函数中任何调⽤Account类的地⽅，我都必须改⽽使⽤_account字段;
 * 3)然后，我修改旧函数，让它将它的⼯作委托给刚完成的这个函数对象;
 * 4)这就是本项重构的基本原则。它带来的好处是：现在我可以轻松地对compute()函数采取 Extract Method （110），不必担⼼参数传递的问题。
 */
public class Gamma {

    private final Account account;
    private final int inputVal;
    private final int quantity;
    private final int yearToDate;


    private int importantValue1;
    private int importantValue2;
    private int importantValue3;


    public Gamma(Account account,
                 int inputVal,
                 int quantity,
                 int yearToDate) {

        this.account = account;

        this.inputVal = inputVal;
        this.quantity = quantity;
        this.yearToDate = yearToDate;
    }


    int compute() {

        importantValue1 = (inputVal * quantity) + account.delta();
        importantValue2 = (inputVal * yearToDate) + 100;

        if ((yearToDate - importantValue1) > 100) {
            importantValue2 -= 20;
        }

        importantValue3 = importantValue2 * 7;

        // and so on.
        return importantValue3 - 2 * importantValue1;
    }
}
