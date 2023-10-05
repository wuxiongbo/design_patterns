package the_beauty_of_design_patterns.chapter64;

//import the_beauty_of_design_patterns.chapter64.demo1.statemachine.v0.MarioStateMachine;
import the_beauty_of_design_patterns.chapter64.statemachine.dependence.State;
import the_beauty_of_design_patterns.chapter64.statemachine.v4.MarioStateMachine;

/**
 * <p>描述类的信息</p>
 *
 * 今天我们讲解了 状态模式。
 * 虽然网上有各种状态模式的定义，但是你只要记住  “状态模式 是 状态机 的一种实现方式” 即可。
 *
 * 状态机 又叫 有限状态机，它有 3 个部分组成：
 *      1） 状态、
 *      2） 事件、
 *      3） 动作。
 *
 * 其中，‘事件’ 也称为 ‘转移条件’。‘事件’ 触发 状态的转移 及 动作的执行。
 * 不过，‘动作’ 不是必须的，也可能只 转移状态，不执行任何动作。
 *
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

    private static void print(MarioStateMachine mario){
        int score = mario.getScore();
        State state = mario.getCurrentState();

        System.out.println("mario score: " + score + "; state: " + state);
    }
}
