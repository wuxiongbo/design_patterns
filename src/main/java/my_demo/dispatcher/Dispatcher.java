package my_demo.dispatcher;

import my_demo.dispatcher.view.HomeView;
import my_demo.dispatcher.view.StudentView;

/**
 * <p> 分发器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/3/15
 * </pre>
 */
public class Dispatcher {

    private StudentView studentView;
    private HomeView homeView;

    public Dispatcher(){
        studentView = new StudentView();
        homeView = new HomeView();
    }


    // 将请求分派
    public void dispatch(String request){
        if(request.equalsIgnoreCase("STUDENT")){
            studentView.show();
        }
        else{
            homeView.show();
        }
    }
}
