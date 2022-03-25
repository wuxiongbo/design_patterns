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
    private Dispatcher dispatcher;

    public FrontController(){
        dispatcher = new Dispatcher();
    }

    // 用户授权
    private boolean isAuthenticUser(){
        System.out.println("User is authenticated successfully.");
        return true;
    }

    private void trackRequest(String request){
        System.out.println("Page requested: " + request);
    }


    public void dispatchRequest(String request){
        // request 日志
        trackRequest(request);

        if(isAuthenticUser()){
            dispatcher.dispatch(request);
        }
    }

}
