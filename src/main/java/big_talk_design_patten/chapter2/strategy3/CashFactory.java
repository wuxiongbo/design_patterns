package big_talk_design_patten.chapter2.strategy3;

public class CashFactory {

    //收费工厂
    public static CashSuper createCashAccept(int cashType){
        CashSuper cs = switch (cashType) {
            case 1 -> new CashNormal();      //正常收费
            case 2 -> new CashRebate(0.8d);  //打八折
            case 3 -> new CashRebate(0.7d);  //打七折
            case 4 -> new CashReturn(300d, 100d);
            default -> null;//满300返100
        };
        return cs;
    }
    
}
