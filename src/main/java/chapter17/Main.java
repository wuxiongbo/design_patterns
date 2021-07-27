package chapter17;

/**
 * <p> 里式替换原则</p>
 *
 * 父类 Transporter 使用 org.apache.http 库中的 HttpClient 类来传输网络数据。
 * 子类 SecurityTransporter 继承父类 Transporter，增加了额外的功能，支持传输 appId 和 appToken 安全认证信息。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Main {
    public static void main(String[] args){

        Demo demo = new Demo();
        demo.demoFunction(new SecurityTransporter(null,null,null));

    }
}
