package the_beauty_of_design_patterns.chapter64.statemachine.v3.state;

import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public interface IMario {
    State getName();

    //以下是定义的事件
    void obtainMushRoom();
    void obtainCape();
    void obtainFireFlower();
    void meetMonster();

}
