package my_demo.monitor.callback.hollywood.demo2.lower;

/**
 * <p>导演</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Director0 {

    private int x; //复制工作的进度

    // 被动 由演员查询进度
    public int getX(){ return x;}

    public void copy() {

        while(x<100){
            try{
                // 更新进度
                Thread.sleep((long)(100*Math.random()));
                x++;
            }catch(InterruptedException e){}
        }

    }
}
