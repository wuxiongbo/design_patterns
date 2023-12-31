package refactoring.chapter06.section8.v3;

public class Account {

    int gamma(int inputVal, int quantity, int yearToDate) {

        return new Gamma(this, inputVal, quantity, yearToDate).compute();
    }

    public int delta() {
        return 0;
    }


}
