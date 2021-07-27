package chapter17;

import chapter17.other.Request;
import chapter17.other.Response;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Demo {
    public void demoFunction(Transporter transporter) {

        Request request = new Request();
        //...省略设置request中数据值的代码...


        Response response = transporter.sendRequest(request);

        //...省略其他逻辑...
    }


}
