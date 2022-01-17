package chapter64.demo1.statemachine.v4.state.impl;

import chapter64.demo1.State;
import chapter64.demo1.statemachine.v4.MarioStateMachine;
import chapter64.demo1.statemachine.v4.state.IMario;

/**
 * <p> 斗篷形态 </p>
 *
 * ‘状态类’ 不需要依赖 MarioStateMachine
 *
 * 通过 函数参数 将 MarioStateMachine 传递进 ‘状态类’。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class CapeMario implements IMario {

    private static final CapeMario instance = new CapeMario();
    private CapeMario() {}
    public static CapeMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.CAPE;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine stateMachine) {
        // do nothing...
    }

    @Override
    public void obtainCape(MarioStateMachine stateMachine) {
        // do nothing...
    }

    @Override
    public void obtainFireFlower(MarioStateMachine stateMachine) {
        // do nothing...
    }

    @Override
    public void meetMonster(MarioStateMachine stateMachine) {
        stateMachine.setCurrentState(SmallMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() - 200);
    }
}
