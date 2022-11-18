package my_demo.dispatcher;

/**
 * <p> 前端控制器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/3/15
 * </pre>
 */
public class FrontController {
    private final Dispatcher dispatcher;

    public FrontController(){
        dispatcher = new Dispatcher();
    }

    // 用户授权
    private boolean isAuthenticUser(){
        System.out.println("User is authenticated successfully.");
        return true;
    }

    // request 日志
    private void trackRequest(String request){
        System.out.println("Page requested: " + request);
    }


    public void dispatchRequest(String request){

        trackRequest(request);
        if(isAuthenticUser()){
            dispatcher.dispatch(request);
        }
    }




    public static void main(String[] args){
        FrontController frontController = new FrontController();

        frontController.dispatchRequest("HOME");
        System.out.println("====================================");
        frontController.dispatchRequest("STUDENT");
    }

}
