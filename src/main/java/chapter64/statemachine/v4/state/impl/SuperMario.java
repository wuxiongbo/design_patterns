package chapter64.statemachine.v4.state.impl;

import chapter64.statemachine.dependence.State;
import chapter64.statemachine.v4.MarioStateMachine;
import chapter64.statemachine.v4.state.IMario;

/**
 * <p> 超级形态 </p>
 *
 * ‘状态类’ 不需要依赖 MarioStateMachine
 *
 * 通过 函数参数 将 MarioStateMachine 传递进 本类‘状态类’。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class SuperMario implements IMario {

    private static final SuperMario instance = new SuperMario();
    private SuperMario() {}
    public static SuperMario getInstance() {
        return instance;
    }



    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine stateMachine) {
        // do nothing...
    }

    @Override
    public void obtainCape(MarioStateMachine stateMachine) {
        // 状态切换
        stateMachine.setCurrentState(CapeMario.getInstance());

        stateMachine.setScore(stateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower(MarioStateMachine stateMachine) {
        // 状态切换
        stateMachine.setCurrentState(FireMario.getInstance());

        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster(MarioStateMachine stateMachine) {
        // 状态切换
        stateMachine.setCurrentState(SmallMario.getInstance());

        stateMachine.setScore(stateMachine.getScore() - 100);
    }
}