package my_demo.monitor.callback.hollywood.demo4.lower;

/**
 * <p>演员</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public interface IPerformer {
    void update(int i);

    default void register(Director2 director2){
        director2.register(this);
    }

}
