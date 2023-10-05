package the_beauty_of_design_patterns.chapter57.eventbus;

import com.google.common.base.Preconditions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>  3.ObserverRegistry  </p>
 *
 * ObserverRegistry 类就是前面讲到的 Observer 注册表，是最复杂的一个类，框架中几乎所有的核心逻辑都在这个类中。
 *
 * 这个类大量使用了 Java 的反射语法，不过代码整体来说都不难理解，
 * 其中，一个比较有技巧的地方是 CopyOnWriteArraySet 的使用。
 * CopyOnWriteArraySet，顾名思义，在写入数据的时候，会创建一个新的 set，并且将原始数据 clone 到新的 set 中，
 * 在新的 set 中写入数据完成之后，再用新的 set 替换老的 set。
 *
 * 这样就能保证在写入数据的时候，不影响数据的读取操作，以此来解决读写并发问题。
 *
 * 除此之外，CopyOnWriteSet 还通过加锁的方式，避免了并发写冲突。
 *
 * 具体的作用你可以去查看一下 CopyOnWriteSet 类的源码，一目了然。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */

public class ObserverRegistry {

    // k: 观察者中被  @Subscribe 注解修饰的 方法 的第一个参数的类型
    // v: 观察者中被  @Subscribe 注解修饰的 方法
    private ConcurrentMap<Class<?>, CopyOnWriteArraySet<ObserverAction>> registry = new ConcurrentHashMap<>();

    // 注册观察者
    public void register(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);
        for (Map.Entry<Class<?>, Collection<ObserverAction>> entry : observerActions.entrySet()) {

            // 观察者中的事件的类型
            Class<?> eventType = entry.getKey();
            // 当前 观察者 中 所有 被 @Subscribe注解修饰的方法。
            Collection<ObserverAction> eventActions = entry.getValue();


            // 转为线程安全的数据结构。
            CopyOnWriteArraySet<ObserverAction> registeredEventActions = registry.get(eventType);
            if (registeredEventActions == null) {
                registry.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
                registeredEventActions = registry.get(eventType);
            }
            registeredEventActions.addAll(eventActions);
        }
    }

    // 查找 匹配的 事件类型：
    // 获取所有被  @Subscribe注解修饰的方法中 ，第一个参数类型匹配的 方法。
    public List<ObserverAction> getMatchedObserverActions(Object event) {
        List<ObserverAction> matchedObservers = new ArrayList<>();

        Class<?> postedEventType = event.getClass();
        for (Map.Entry<Class<?>, CopyOnWriteArraySet<ObserverAction>> entry : registry.entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<ObserverAction> eventActions = entry.getValue();

            // 判断 eventType(被观察者类型) 是否为 postedEventType(传入的事件类型)  的 父类
            if (eventType.isAssignableFrom(postedEventType)) {
                matchedObservers.addAll(eventActions);
            }
        }
        return matchedObservers;
    }

    // 查找观察者中，所有被 @Subscribe 注解 修饰的方法。
    private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer) {

        Map<Class<?>, Collection<ObserverAction>> observerActions = new HashMap<>();

        Class<?> clazz = observer.getClass();
        for (Method method : getAnnotatedMethods(clazz)) {
            // 方法的参数类型 数组
            Class<?>[] parameterTypes = method.getParameterTypes();
            // 方法的第一个参数的类型
            Class<?> eventType = parameterTypes[0];
            if (!observerActions.containsKey(eventType)) {
                observerActions.put(eventType, new ArrayList<>());
            }
            observerActions.get(eventType).add(new ObserverAction(observer, method));
        }
        return observerActions;
    }

    // 查找类中 @Subscribe 注解的方法
    private List<Method> getAnnotatedMethods(Class<?> clazz) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Preconditions.checkArgument(
                        parameterTypes.length == 1,
                        "Method %s has @Subscribe annotation but has %s parameters."
                                + "Subscriber methods must have exactly 1 parameter.",
                        method,
                        parameterTypes.length);
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
