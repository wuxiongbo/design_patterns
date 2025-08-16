package design_patterns.chapter64.statemachine.v3.state.impl;

import design_patterns.chapter64.statemachine.dependence.State;
import design_patterns.chapter64.statemachine.v3.MarioStateMachine;
import design_patterns.chapter64.statemachine.v3.state.IMario;

/**
 * <p> 超级马里奥 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class SuperMario implements IMario {

    private MarioStateMachine stateMachine;
    // 依赖注入  状态机
    public SuperMario(MarioStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }



    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom() {
        // do nothing...
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
        stateMachine.setCurrentState(new SmallMario(stateMachine));
        stateMachine.setScore(stateMachine.getScore() - 100);
    }
}