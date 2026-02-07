package design_patterns.chapter22.demo2.v3;

import design_patterns.chapter22.demo2.v3.serialization.interface1.Deserializable;

/**
 * <p>描述类的信息</p>
 *
 * 有依赖关系的类之间，尽量只依赖必要的接口
 *
 * 只用到反序列化操作的类，不应该依赖序列化接口
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class DemoClass_2 {

    // 巧妙的通过接口，对功能进行了隔离。  本类只能调序列化方法，而调不到反序列化方法。
    private final Deserializable deserializer;

    public DemoClass_2(Deserializable deserializer) {
        this.deserializer = deserializer;
    }


    //...

}
