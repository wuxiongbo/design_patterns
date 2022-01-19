package chapter64.statemachine.v4;

import chapter64.State;
import chapter64.statemachine.v4.state.IMario;
import chapter64.statemachine.v4.state.impl.SmallMario;

/**
 * <p>描述类的信息</p>
 *
 *
 * MarioStateMachine 依赖各个 状态类 是理所当然的
 *
 *
 * 实际上，像游戏这种比较复杂的状态机，包含的状态比较多，我优先推荐使用 ‘查表法’ ，
 * 状态模式 会引入非常多的状态类，会导致代码比较难维护。
 *
 * 相反，像电商下单、外卖下单这种类型的状态机，它们的状态并不多，状态转移也比较简单，
 * 但 ‘事件’触发执行的‘动作’ 所包含的业务逻辑可能会比较复杂，所以，更加推荐使用 ‘状态模式’ 来实现。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class MarioStateMachine {
    private int score;
    private IMario currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }




    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }

    public void obtainCape() {
        this.currentState.obtainCape(this);
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }

    public void meetMonster() {
        this.currentState.meetMonster(this);
    }





    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState.getName();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }


}
