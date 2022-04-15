package my_demo.monitor.callback.demo4.lower;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Director2 {
    // 记录对本导演感兴趣的演员们
    private List<IPerformer > listeners = new ArrayList<>();//电话簿
    public void register(IPerformer listener) {//监听器注册
        listeners.add(listener);
    }

    public void copy() {
        for(int i=0;i<100;i++){

            // 在适当的时机调用回调 ，时机由我定
            try{
                Thread.sleep((long)(100*Math.random()));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i%10 == 0) {
                // 通知演员们
                for(IPerformer performer: listeners) {
                    performer.update(i);
                }
            }
        }
        System.out.println("copy() over");
    }
}
