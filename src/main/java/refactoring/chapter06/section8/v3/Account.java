package refactoring.chapter06.section8.v3;

public class Account {

    int gamma(int inputVal, int quantity, int yearToDate) {
        // 将它的工作委托给Gamma类
        return new Gamma(
                this,
                inputVal,
                quantity,
                yearToDate).compute();

    }

    public int delta() {
        return 0;
    }


}
