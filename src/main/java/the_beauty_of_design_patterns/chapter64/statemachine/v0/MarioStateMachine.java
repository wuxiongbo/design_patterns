package the_beauty_of_design_patterns.chapter64.statemachine.v0;

import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;

/**
 * <p>状态机</p>
 *
 * 事件:
 *   Event1   获得 蘑菇      MushRoom
 *   Event2   获得 斗篷      Cape
 *   Event3   获得 火焰      FireFlower
 *   Event4   遇到怪物       Monster
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
        //TODO  事件Event1
    }

    public void obtainCape() {
        //TODO  事件Event2
    }

    public void obtainFireFlower() {
        //TODO  事件Event3
    }

    public void meetMonster() {
        //TODO  事件Event4
    }


    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}
