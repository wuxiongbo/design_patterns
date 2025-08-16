package design_patterns.chapter64.statemachine.v2;

import design_patterns.chapter64.statemachine.dependence.State;

/**
 * <p>状态机 实现方式二：查表法</p>
 *
 * 事件:
 *   E1   获得 蘑菇      MushRoom
 *   E2   获得 斗篷      Cape
 *   E3   获得 火焰      FireFlower
 *
 *   E4   遇到怪物       Monster
 *
 *
 * 实现思路：
 *   状态机 除了用 ‘状态转移图’ 来表示之外，还可以用 ‘二维表’ 来表示，如下所示。
 *   在 二维表 中，
 *        第一维 表示当前状态，
 *        第二维 表示 事件，
 *        值 表示 当前状态 经过 事件 之后， ‘转移到的新状态’ 及 ‘执行的动作’。
 *
 * 缺点：
 *   在查表法的代码实现中，事件触发的动作只是简单的积分加减，
 *   所以，我们用一个 int 类型的二维数组 actionTable 就能表示，二维数组中的值 表示积分的加减值。
 *   但是，如果要执行的动作并非这么简单，而是一系列复杂的逻辑操作（比如加减积分、写数据库，还有可能发送消息通知等等），
 *   我们就没法用如此简单的二维数组来表示了。
 *   这也就是说，查表法的实现方式有一定局限性。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class MarioStateMachine {

    private int score;
    private State currentState;

    /**
     * 状态切换表
     * 列：事件， 行：状态
     */
    private static final State[][] TRANSITION_TABLE = {
            // E1           E2          E3           E4      Even   State
            {State.SUPER, State.CAPE, State.FIRE, State.SMALL},  // SMALL
            {State.SUPER, State.CAPE, State.FIRE, State.SMALL},  // SUPER
            {State.CAPE, State.CAPE, State.CAPE, State.SMALL},   // CAPE
            {State.FIRE, State.FIRE, State.FIRE, State.SMALL}    // FIRE
    };

    /**
     * 分数行为表
     * 列：事件， 行：状态
     */
    private static final int[][] ACTION_TABLE = {
            // E1    E2    E3   E4    Even         State
            {+100, +200, +300, +0},             // SMALL
            {+0,   +200, +300, -100},           // SUPER
            {+0,   +0,   +0,   -200},           // CAPE
            {+0,   +0,   +0,   -300}            // FIRE
    };

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMushRoom() {
        executeEvent(Event.GOT_MUSHROOM);
    }

    public void obtainCape() {
        executeEvent(Event.GOT_CAPE);
    }

    public void obtainFireFlower() {
        executeEvent(Event.GOT_FIRE);
    }

    public void meetMonster() {
        executeEvent(Event.MET_MONSTER);
    }

    private void executeEvent(Event event) {
        // 获取 状态、事件
        int stateValue = currentState.getValue();
        int eventValue = event.getValue();


        // 查 状态切换表
        this.currentState = TRANSITION_TABLE[stateValue][eventValue];

        // 查 分数行为表
        this.score += ACTION_TABLE[stateValue][eventValue];
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}
