package the_beauty_of_design_patterns.chapter64.statemachine.v4.state.impl;

import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;
import the_beauty_of_design_patterns.chapter64.statemachine.v4.MarioStateMachine;
import the_beauty_of_design_patterns.chapter64.statemachine.v4.state.IMario;

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
        // 状态切换
        stateMachine.setCurrentState(SmallMario.getInstance());

        stateMachine.setScore(stateMachine.getScore() - 200);
    }
}
