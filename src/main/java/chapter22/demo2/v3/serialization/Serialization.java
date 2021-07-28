package chapter22.demo2.v3.serialization;

import chapter22.demo2.v3.serialization.interface1.Deserializable;
import chapter22.demo2.v3.serialization.interface1.Serializable;

/**
 * <p>描述类的信息</p>
 *
 * 如果我们既不想违背高内聚的设计思想，也不想违背迪米特法则，那我们该如何解决这个问题呢？
 *
 * 实际上，通过引入两个接口就能轻松解决这个问题
 *
 *
 * 功能通过两个接口进行拆分。  实现放在一个类当中，实现 “高内聚”。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Serialization implements Serializable, Deserializable {
    @Override
    public String serialize(Object object) {
        String serializedResult = /*...*/null;
//    ...
        return serializedResult;
    }

    @Override
    public Object deserialize(String str) {
        Object deserializedResult = /*...*/null;
//    ...
        return deserializedResult;
    }
}
