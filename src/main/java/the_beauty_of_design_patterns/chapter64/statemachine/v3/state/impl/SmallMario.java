package the_beauty_of_design_patterns.chapter64.statemachine.v3.state.impl;

import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;
import the_beauty_of_design_patterns.chapter64.statemachine.v3.MarioStateMachine;
import the_beauty_of_design_patterns.chapter64.statemachine.v3.state.IMario;

/**
 * <p> 形态类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class SmallMario  implements IMario {


    private MarioStateMachine stateMachine;

    // 依赖注入  状态机
    public SmallMario(MarioStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom() {
        stateMachine.setCurrentState(new SuperMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() + 100);
    }

    @Override
    public void obtainCape() {
        stateMachine.setCurrentState(new CapeMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower() {
        stateMachine.setCurrentState(new FireMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster() {
        // do nothing...
    }
}