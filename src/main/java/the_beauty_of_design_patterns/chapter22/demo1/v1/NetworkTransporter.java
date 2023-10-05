package the_beauty_of_design_patterns.chapter22.demo1.v1;

/**
 * <p>网络通讯器</p>
 *
 *  作为一个底层网络通信类，我们希望它的功能尽可能通用，而不只是服务于下载 HTML，
 *  所以，我们不应该直接依赖太具体的发送对象 HtmlRequest。
 *
 *  从这一点上讲，NetworkTransporter 类的设计违背 迪米特法则 ，依赖了不该有直接依赖关系的 HtmlRequest 类。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class NetworkTransporter {
    // 省略属性和其他方法...



    // 改造前。
    // 违背 迪米特法则，因为 依赖了不该有直接依赖关系的 HtmlRequest 类。
//    public Byte[] send(HtmlRequest htmlRequest) {
//        //...
//        return null;
//    }

    // 改造后。
    public Byte[] send(String address, Byte[] data) {
        //...
        return null;
    }

}
