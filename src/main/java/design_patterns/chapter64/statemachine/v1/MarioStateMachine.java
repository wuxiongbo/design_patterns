package design_patterns.chapter64.statemachine.v1;

import design_patterns.chapter64.statemachine.dependence.State;

/**
 * <p>状态机 实现方式一：分支逻辑法</p>
 * <p>
 * 事件:
 * E1   获得 蘑菇      MushRoom
 * E2   获得 斗篷      Cape
 * E3   获得 火焰      FireFlower
 * E4   遇到怪物       Monster
 * <p>
 * <p>
 * 对于如何实现状态机，我总结了三种方式:
 * 1) 分支逻辑法
 * 2) 查表法
 * 3) 状态模式
 * <p>
 * 其中，最简单直接的实现方式是，参照 状态转移图 ，将每一个状态转移，原模原样地直译成代码。
 * 这样编写的代码会包含大量的 if-else 或 switch-case 分支判断逻辑，甚至是 嵌套的分支判断逻辑，所以，我把这种方法暂且命名为 分支逻辑法。
 * <p>
 * 按照这个实现思路，我将上面的骨架代码补全一下。
 * 补全之后的代码如下所示：
 * 见代码
 * <p>
 * <p>
 * 缺点：
 * 第一种实现方法有点类似 hard code 硬编码，对于复杂的状态机来说不适用，而状态机的第二种实现方式 "查表法"，就更加合适了。
 * 接下来，我们就一块儿来看下，如何利用 '查表法' 来补全 骨架代码。
 *
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

    /**
     * 获取的蘑菇
     */
    public void obtainMushRoom() {
        if (currentState.equals(State.SMALL)) {
            this.currentState = State.SUPER;
            this.score += 100;
        }
    }

    /**
     * 获得的斗篷
     */
    public void obtainCape() {
        if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER)) {
            this.currentState = State.CAPE;
            this.score += 200;
        }
    }

    /**
     * 获得火花
     */
    public void obtainFireFlower() {
        if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER)) {
            this.currentState = State.FIRE;
            this.score += 300;
        }
    }

    /**
     * 遇到怪物
     */
    public void meetMonster() {
        if (currentState.equals(State.SUPER)) {
            this.currentState = State.SMALL;
            this.score -= 100;
            return;
        }

        if (currentState.equals(State.CAPE)) {
            this.currentState = State.SMALL;
            this.score -= 200;
            return;
        }

        if (currentState.equals(State.FIRE)) {
            this.currentState = State.SMALL;
            this.score -= 300;
            return;
        }
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}