package the_beauty_of_design_patterns.chapter22.demo2.v3;

import the_beauty_of_design_patterns.chapter22.demo2.v3.serialization.Serialization;

/**
 * <p> 高内聚，松耦合 示例</p>
 *
 *
 * 所谓高内聚，就是指
 *    相近的功能应该放到同一个类中，不相近的功能不要放到同一类中。
 *    相近的功能往往会被同时修改，放到同一个类中，修改会比较集中。
 *
 * 所谓松耦合,指的是
 *    在代码中，类与类之间的依赖关系简单清晰。
 *    即使两个类有依赖关系，一个类的代码改动也不会或者很少导致依赖类的代码改动。
 *
 *
 * 如何理解“迪米特法则”？
 *    不该有直接依赖关系的类之间，不要有依赖；
 *    有依赖关系的类之间，尽量只依赖必要的接口。
 *
 * 设计原则 迪米特法则， 是希望减少类之间的耦合，让类越独立越好。每个类都应该少了解系统的其他部分。一旦发生变化，需要了解这一变化的类就会比较少。
 *
 *
 * 利用 迪米特法则 来实现“高内聚、松耦合”
 *
 *
 *
 * 尽管我们还是要往 DemoClass_1 的构造函数中，传入包含序列化和反序列化的 Serialization 实现类，
 * 但是，我们依赖的 Serializable 接口 只包含序列化操作，DemoClass_1 无法使用 Serialization 类中的反序列化接口，对反序列化操作无感知。
 *
 * 这就符合了 迪米特法则 后半部分所说的“依赖有限接口”的要求。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Main {
    public static void main(String[] args){

        Serialization serialization = new Serialization();

        DemoClass_1 demoClass_1 = new DemoClass_1(serialization);
        DemoClass_2 demoClass_2 = new DemoClass_2(serialization);

    }
}
