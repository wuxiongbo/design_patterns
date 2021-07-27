package chapter22.demo2.v2;

/**
 * <p>描述类的信息</p>
 * 分之后的代码更能满足迪米特法则，但却违背了高内聚的设计思想。
 *
 * 高内聚要求相近的功能要放到同一个类中，这样可以方便功能修改的时候，修改的地方不至于过于分散。
 *
 * 对于本案例来说，如果我们修改了序列化的实现方式，比如从 JSON 换成了 XML，那反序列化的实现逻辑也需要一并修改。
 * 在未拆分的情况下，我们只需要修改一个类即可。在拆分之后，我们需要修改两个类。显然，这种设计思路的代码改动范围变大了。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Serializer {

    // 序列化方法
    public String serialize(Object object) {

        String serializedResult = /*...*/null;

        //    ...

        return serializedResult;
    }





}
