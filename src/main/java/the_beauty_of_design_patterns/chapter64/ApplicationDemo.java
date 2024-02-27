package the_beauty_of_design_patterns.chapter64;

//import the_beauty_of_design_patterns.chapter64.demo1.statemachine.v0.MarioStateMachine;

import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;
import the_beauty_of_design_patterns.chapter64.statemachine.v4.MarioStateMachine;

/**
 * <p>描述类的信息</p>
 * <p>
 * 今天我们讲解了 状态模式。
 * 虽然网上有各种状态模式的定义，但是你只要记住  “状态模式 只是 状态机 的一种实现方式” 即可。
 * <p>
 * 状态机 又叫 有限状态机，它有 3 个部分组成：
 * 1） 状态
 * 2） 事件
 * 3） 动作
 * <p>
 * 其中，‘事件’ 也称为 ‘转移条件’。‘事件’ 触发 '状态'的转移 及 '动作'的执行。
 * 不过，‘动作’ 不是必须的，也可能只 转移'状态'，不执行任何动作。
 * <p>
 * 状态State：一般在状态转移图中用圆圈表示。
 * 事件Event：表示从一种状态迁移到另一种状态的触发机制。对应状态转换图中的箭头部分。
 * 动作Action: 表示状态转换后，执行的动作，但不是必须的，也可以状态转换后不执行任何动作。
 * 转移Transition：表示状态转换，从原始状态迁移到目的状态的一个过程。
 * 条件Guard：表示发生状态转移需满足的条件。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class ApplicationDemo {
    public static void main(String[] args) {

        // 初始为  small 状态
        MarioStateMachine mario = new MarioStateMachine();

        // 吃蘑菇
        mario.obtainMushRoom();
        print(mario);

        // 斗篷
        mario.obtainCape();
        print(mario);

        // 火焰
        mario.obtainFireFlower();
        print(mario);

        // 怪物
        mario.meetMonster();
        print(mario);


    }

    private static void print(MarioStateMachine mario) {
        int score = mario.getScore();
        State state = mario.getCurrentState();

        System.out.println("mario score: " + score + "; state: " + state);
    }
}
