package design_patterns.chapter15.v2;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Map;

/**
 * <p>单一职责。 反例</p>
 *
 * 虽然经过拆分之后，Serializer 类和 Deserializer 类的职责更加单一了，但也随之带来了新的问题。
 *
 * 1.如果，我们修改了协议的格式，数据标识从“UEUEUE”改为“DFDFDF”，或者序列化方式从 JSON 改为了 XML，
 *   那 ，Serializer 类和 Deserializer 类都需要做相应的修改，代码的内聚性显然没有原来 Serialization 高了。
 * 2.如果我们仅仅对 Serializer 类做了协议修改，而忘记了修改 Deserializer 类的代码，那就会导致序列化、反序列化不匹配，程序运行出错，
 *   也就是说，拆分之后，代码的可维护性变差了。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Deserializer {
    private static final String IDENTIFIER_STRING = "UEUEUE;";
    private Gson gson;

    public Deserializer() {
        this.gson = new Gson();
    }

    public Map<String, String> deserialize(String text) {
        if (!text.startsWith(IDENTIFIER_STRING)) {
            return Collections.emptyMap();
        }
        String gsonStr = text.substring(IDENTIFIER_STRING.length());
        return gson.fromJson(gsonStr, Map.class);
    }
}
