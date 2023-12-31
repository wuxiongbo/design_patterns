package big_talk_design_patten.chapter2.strategy4;

public class CashNormal extends big_talk_design_patten.chapter2.strategy4.CashSuper {

    //正常收费，原价返回
    public double acceptCash(double price, int num) {
        return price * num;
    }

}
