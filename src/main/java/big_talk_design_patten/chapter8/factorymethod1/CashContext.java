package big_talk_design_patten.chapter8.factorymethod1;

public class CashContext {
    private ISale cs;   //声明一个ISale接口对象
    //通过构造方法，传入具体的收费策略
    public CashContext(int cashType){
        IFactory fs = switch (cashType) {
            case 1 ->//原价
                    new CashRebateReturnFactory(1d, 0d, 0d);
            case 2 ->//打8折
                    new CashRebateReturnFactory(0.8d, 0d, 0d);
            case 3 ->//打7折
                    new CashRebateReturnFactory(0.7d, 0d, 0d);
            case 4 ->//满300返100
                    new CashRebateReturnFactory(1, 300d, 100d);
            case 5 ->//先打8折,再满300返100
                    new CashRebateReturnFactory(0.8d, 300d, 100d);
            case 6 ->//先满200返50，再打7折
                    new CashReturnRebateFactory(0.7d, 200d, 50d);
            default -> null;
        };
        this.cs = fs.createSalesModel();
    }

    public double getResult(double price,int num){
        //根据收费策略的不同，获得计算结果
        return this.cs.acceptCash(price,num);
    }    
}


