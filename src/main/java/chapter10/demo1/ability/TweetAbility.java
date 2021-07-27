package chapter10.demo1.ability;

import chapter10.demo1.interface1.Tweetable;

/**
 * <p>实现会叫的能力</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class TweetAbility implements Tweetable {
    @Override
    public void tweet() {
        System.out.println("叫（委托实现）");
    }
}