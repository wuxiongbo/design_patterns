package design_patterns.chapter17;

import design_patterns.chapter17.lsp.Client;
import design_patterns.chapter17.lsp.SecurityTransporter;
import design_patterns.chapter17.lsp.Transporter;

/**
 * <p> 里式替换原则</p>
 *
 * 里式替换原则 Liskov Substitution Principle，缩写为 LSP
 *
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

        Client client = new Client();


        Transporter transporter = new SecurityTransporter(null, "your-appId", "your-appToken");


        client.demoFunction(transporter);

    }
}
