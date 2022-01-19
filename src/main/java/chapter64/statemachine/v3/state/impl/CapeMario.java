package chapter64.statemachine.v3.state.impl;

import chapter64.State;
import chapter64.statemachine.v3.MarioStateMachine;
import chapter64.statemachine.v3.state.IMario;

/**
 * <p> 形态类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class CapeMario  implements IMario {

    private MarioStateMachine stateMachine;

    // 依赖注入  状态机
    public CapeMario(MarioStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public State getName() {
        return State.CAPE;
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
        stateMachine.setScore(stateMachine.getScore() - 200);
    }
}
