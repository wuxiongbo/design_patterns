package big_talk_design_patten.chapter2.strategy3;

import big_talk_design_patten.chapter2.strategy3.cash.CashNormal;
import big_talk_design_patten.chapter2.strategy3.cash.CashRebate;
import big_talk_design_patten.chapter2.strategy3.cash.CashReturn;
import big_talk_design_patten.chapter2.strategy3.cash.CashSuper;

/**
 * 简单工厂模式虽然也能解决这个问题，但这个模式只是解决对象的创建问题，
 * 而且, 由于工厂本身包括所有的收费方式，商场是可能经常性地更改打折额度和返利额度，
 * 每次维护或扩展收费方式都要改动这个工厂，以致代码需重新编译部署，这真的是很糟糕的处理方式，所以用它不是最好的办法。
 *
 *
 * 简单工厂并未对生产的对象有什么行为的要求,接下来将看到, 策略模式 要求 策略有同样的行为抽象.
 * 这将有个最大的优势. 可以将策略的统一行为抽象出来调用. 这将使客户端代码, 对策略类无感知,只与策略上下文进行交互.
 *
 */
public class CashFactory {

    //收费工厂
    public static CashSuper createCashAccept(int cashType) {
        return switch (cashType) {
            case 1 -> new CashNormal();      //正常收费
            case 2 -> new CashRebate(0.8d);  //打八折
            case 3 -> new CashRebate(0.7d);  //打七折
            case 4 -> new CashReturn(300d, 100d);
            default -> null;//满300返100
        };
    }

}
