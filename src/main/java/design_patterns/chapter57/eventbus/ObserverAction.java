package design_patterns.chapter57.eventbus;

import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p> 2.ObserverAction </p>
 *
 * ObserverAction 类用来表示 @Subscribe 注解的方法，
 * 其中，
 *      target 表示 观察者类，
 *      method 表示 方法。
 *
 * 它主要用在 ObserverRegistry 观察者注册表中。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */

public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        this.method.setAccessible(true);
    }

    // event是 method所代表的方法的  方法参数
    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
