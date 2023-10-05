package the_beauty_of_design_patterns.chapter64.statemachine.v0;

import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;

/**
 * <p>状态机</p>
 *
 * 事件:
 *   E1   获得 蘑菇      MushRoom
 *   E2   获得 斗篷      Cape
 *   E3   获得 火焰      FireFlower
 *
 *   E4   遇到怪物       Monster
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class MarioStateMachine {

    private int score;
    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }


    public void obtainMushRoom() {
        //TODO  事件E1
    }

    public void obtainCape() {
        //TODO  事件E2
    }

    public void obtainFireFlower() {
        //TODO  事件E3
    }

    public void meetMonster() {
        //TODO  事件E4
    }


    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}
