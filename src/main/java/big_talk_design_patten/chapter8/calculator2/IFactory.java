package big_talk_design_patten.chapter8.calculator2;

public interface IFactory {

    public Operation createOperation(String operType);
    
}
