package big_talk_design_patten.chapter2.strategy5;

import big_talk_design_patten.chapter2.strategy5.cash.CashNormal;
import big_talk_design_patten.chapter2.strategy5.cash.CashRebate;
import big_talk_design_patten.chapter2.strategy5.cash.CashReturn;
import big_talk_design_patten.chapter2.strategy5.cash.CashSuper;

public class CashContext {

    private CashSuper cs;   //声明一个CashSuper对象

    //通过构造方法，传入具体的收费策略
    public CashContext(int cashType) {
        switch (cashType) {
            case 1 -> this.cs = new CashNormal();
            case 2 -> this.cs = new CashRebate(0.8d);
            case 3 -> this.cs = new CashRebate(0.7d);
            case 4 -> this.cs = new CashReturn(300d, 100d);
        }
    }

    public double getResult(double price, int num) {
        //根据收费策略的不同，获得计算结果
        return this.cs.acceptCash(price, num);
    }
}
