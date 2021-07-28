package chapter44.v5;

import chapter44.dependence.interface1.IRuleConfigParser;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public interface IRuleConfigParserFactory {

    // 当对象的创建逻辑比较复杂，不只是简单的 new 一下就可以，而是要组合其他类对象，做各种初始化操作的时候，
    // 我们推荐使用 工厂方法模式 ，将复杂的创建逻辑拆分到多个工厂类中，让每个工厂类都不至于过于复杂。
    // 这种情况，使用 简单工厂模式 ，将所有的创建逻辑都放到一个工厂类中，将会导致这个工厂类变得很复杂。
    IRuleConfigParser createParser();
}
