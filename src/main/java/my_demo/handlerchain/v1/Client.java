package my_demo.handlerchain.v1;

import my_demo.handlerchain.v1.handle.impl.HeaderTextProcessing;
import my_demo.handlerchain.v1.handle.ProcessingObject;
import my_demo.handlerchain.v1.handle.impl.SpellCheckerProcessing;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/2/24
 * </pre>
 */
public class Client {

    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handler("Aren't labdas really sexy?!!");

        System.out.println(result);
    }

}