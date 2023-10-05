package the_beauty_of_design_patterns.chapter64.statemachine.v4.state.impl;

import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;
import the_beauty_of_design_patterns.chapter64.statemachine.v4.MarioStateMachine;
import the_beauty_of_design_patterns.chapter64.statemachine.v4.state.IMario;

/**
 * <p> 初始形态 </p>
 *
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
public class SmallMario  implements IMario {

    private static final SmallMario instance = new SmallMario();
    private SmallMario() {}
    public static SmallMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine stateMachine) {
        // 状态切换
        stateMachine.setCurrentState(SuperMario.getInstance());

        stateMachine.setScore(stateMachine.getScore() + 100);
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
        // do nothing...
    }
}