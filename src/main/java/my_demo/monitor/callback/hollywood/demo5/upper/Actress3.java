package my_demo.monitor.callback.hollywood.demo5.upper;

import my_demo.monitor.callback.hollywood.demo5.lower.Director3;
import my_demo.monitor.callback.hollywood.demo5.lower.IPerformer;

import java.util.Observable;
import java.util.Observer;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Actress3 implements Observer {

    public Actress3(){
        new Director3().addObserver(this );  // 完成注册
    }


    private class Actress implements IPerformer{
        /*
         * 回调方法。下层模块执行时，传回一些数据。
         */
        @Override
        public void update(int i) {
            System.out.println("进度："+i+"%");
        }
    }


    @Override
    public void update(Observable observable , Object data){//data为任意对象，用于传递参数
        int i = (Integer)data;
        new Actress().update(i);
    }

}