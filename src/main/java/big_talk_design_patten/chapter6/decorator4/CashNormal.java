package big_talk_design_patten.chapter6.decorator4;

public class CashNormal extends CashSuper {

    //正常收费，原价返回
    public double acceptCash(double price,int num){
        return price * num;
    }
    
}
