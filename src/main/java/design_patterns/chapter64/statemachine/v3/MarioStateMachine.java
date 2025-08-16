package design_patterns.chapter64.statemachine.v3;

import design_patterns.chapter64.statemachine.dependence.State;
import design_patterns.chapter64.statemachine.v3.state.IMario;
import design_patterns.chapter64.statemachine.v3.state.impl.SmallMario;

/**
 * <p>状态机 实现方式三：状态模式</p>
 *
 * MarioStateMachine 和各个 ‘状态类’  之间是 “双向依赖”关系。
 *
 * MarioStateMachine 依赖各个 ‘状态类’ 是理所当然的，
 * 但是，反过来，各个 ‘状态类’ 为什么要依赖 MarioStateMachine 呢？
 * 这是因为，各个 ‘状态类’ 需要更新 MarioStateMachine 中的两个变量，score 和 currentState。
 *
 *
 * 实际上，此代码还可以继续优化，我们可以将 ‘状态类’ 设计成 ‘单例’，毕竟，状态类 中不包含任何成员变量。
 * 但是，当 ‘状态类’ 设计成 ‘单例’ 之后，我们就无法通过 构造函数 来传递 MarioStateMachine 了，而 状态类 又要依赖 MarioStateMachine，
 * 那该如何解决这个问题呢？
 *
 * 实际上，在 第 42 讲 单例模式的讲解中，我们提到过几种解决方法，你可以回过头去再查看一下。
 * 在这里，我们可以通过 函数参数 将 MarioStateMachine 传递进 状态类。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class MarioStateMachine {

    private int score;
    // 不再使用枚举来表示状态， 改用实现类
    private IMario currentState;


    public MarioStateMachine() {
        this.score = 0;
        this.currentState = new SmallMario(this);
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





    public void obtainMushRoom() {
        this.currentState.obtainMushRoom();
    }

    public void obtainCape() {
        this.currentState.obtainCape();
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower();
    }

    public void meetMonster() {
        this.currentState.meetMonster();
    }



}
