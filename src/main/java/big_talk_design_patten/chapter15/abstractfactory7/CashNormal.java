package big_talk_design_patten.chapter15.abstractfactory7;

public class CashNormal implements ISale {
    //正常收费，原价返回
    public double acceptCash(double price,int num){
        return price * num; 
    }    
}
