package the_beauty_of_design_patterns.chapter64.statemachine.v3.state.impl;

import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;
import the_beauty_of_design_patterns.chapter64.statemachine.v3.MarioStateMachine;
import the_beauty_of_design_patterns.chapter64.statemachine.v3.state.IMario;

/**
 * <p> 火焰马里奥 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class FireMario implements IMario {

    private MarioStateMachine stateMachine;
    // 依赖注入  状态机
    public FireMario(MarioStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }


    @Override
    public State getName() {
        return State.FIRE;
    }

    @Override
    public void obtainMushRoom() {
        // do nothing...
    }

    @Override
    public void obtainCape() {
        // do nothing...
    }

    @Override
    public void obtainFireFlower() {
        // do nothing...
    }

    @Override
    public void meetMonster() {
        stateMachine.setCurrentState(new SmallMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() - 300);
    }

}
