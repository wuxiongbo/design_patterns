package big_talk_design_patten.chapter8.calculator1;

/**
 * @author Xander Wu
 * @date 2023/12/10 15:17
 */
public interface IFactory {

    public AddFactory.Operation createOperation();

}
