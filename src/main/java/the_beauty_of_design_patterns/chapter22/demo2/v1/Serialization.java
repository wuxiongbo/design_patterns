package the_beauty_of_design_patterns.chapter22.demo2.v1;

import java.util.List;
import java.util.Map;

/**
 * <p>描述类的信息</p>
 *
 *  Serialization 类负责对象的序列化和反序列化。
 *
 *  假设在我们的项目中，有些类只用到了 ‘序列化’操作，而另一些类只用到 ‘反序列化’操作。
 *  那，基于 “迪米特法则”  后半部分 “有依赖关系的类之间，尽量只依赖必要的接口”，只用到 序列化操作 的那部分类，不应该依赖 反序列化接口。
 *  同理，只用到 反序列化操作 的那部分类，不应该依赖 序列化接口。
 *
 *  根据这个思路，我们应该将 Serialization 类拆分为两个更小粒度的类，一个只负责序列化（Serializer 类），一个只负责反序列化（Deserializer 类）。
 *  拆分之后，使用序列化操作的类只需要依赖 Serializer 类，使用反序列化操作的类只需要依赖 Deserializer 类。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */

public class Serialization {

    public String serialize(Object object) {

        String serializedResult = /*...*/null;
        //...

        return serializedResult;
    }


    public Object deserialize(String str) {
        Object deserializedResult = /*...*/ null;
        //...
        return deserializedResult;
    }


    // 序列化 反序列化 方法变多了之后....

// 一旦任一序列化操作有代码改动，我们都需要检查、测试所有依赖 Serialization 类的代码是否还能正常工作。
// 为了减少耦合和测试工作量，我们应该按照 “迪米特法则”，将序列化和反序列化的功能隔离开来。

    public String serializeMap(Map map) {
        //...
        return null;
    }
    public String serializeList(List list) {
        //...
        return null;
    }

    public Map deserializeMap(String mapString) {
        //...
        return null;
    }
    public List deserializeList(String listString) {
        //...
        return null;
    }

    //    ...
}
