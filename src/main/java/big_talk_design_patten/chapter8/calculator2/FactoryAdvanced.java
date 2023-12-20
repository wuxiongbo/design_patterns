package big_talk_design_patten.chapter8.calculator2;

//高级运算工厂
public class FactoryAdvanced implements IFactory {

    public Operation createOperation(String operType){
        Operation oper = switch (operType) {
            case "pow" -> new Pow();//指数运算类实例
            case "log" -> new Log();
            default -> null;//对数运算类实例
            //此处可扩展其他高级运算类的实例化，但修改
            //当前工厂类不会影响到基础运算工厂类

        };

        return oper;
    }    
}





