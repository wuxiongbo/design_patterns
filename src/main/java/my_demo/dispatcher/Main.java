package my_demo.dispatcher;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/3/15
 * </pre>
 */
public class Main {
    public static void main(String[] args){
        FrontController frontController = new FrontController();

        frontController.dispatchRequest("HOME");
        System.out.println("====================================");
        frontController.dispatchRequest("STUDENT");
    }
}
