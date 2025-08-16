package design_patterns.chapter59.hook;

/**
 * <p> Hook </p>
 * <p>
 * Hook 可以翻译成“钩子”，那它跟 Callback 有什么区别呢？
 * <p>
 * 观点一：
 * Hook 就是 Callback，两者说的是一回事儿，只是表达不同而已。
 * 观点二：
 * Hook 是 Callback 的一种 “应用场景”。
 * <p>
 * 即，
 * Callback 更侧重 "语法机制" 的描述，
 * Hook     更侧重 "应用场景" 的描述。
 * <p>
 * 我个人比较认可后面一种说法。
 * <p>
 * 不过，这个也不重要，我们只需要 “见了代码能认识，遇到场景会用”  就可以了。
 * <p>
 * <p>
 * <p>
 * <p>
 * Hook 比较经典的应用场景是 Tomcat 和 JVM 的 shutdown hook。
 * 接下来，我们拿 JVM 来举例说明一下：
 * <p>
 *    JVM 提供了 Runtime.addShutdownHook(Thread hook) 方法，
 *    此方法，可以注册一个 "JVM 关闭" 的 Hook。
 *    当应用程序关闭的时候，JVM 会自动调用 Hook 代码。
 * 代码示例如下所示：
 * <p>
 * 从代码中我们可以发现，
 * 1）有关 Hook 的逻辑，都被封装到 ApplicationShutdownHooks 类中了。
 * {@link java.lang.ApplicationShutdownHooks#add(java.lang.Thread)}
 * <p>
 * 2）当应用程序关闭的时候，JVM 会调用 ApplicationShutdownHooks 类的 runHooks() 方法，
 * 创建多个线程，并发地执行多个 Hook。
 * {@link java.lang.ApplicationShutdownHooks#runHooks()}
 * <p>
 * 我们在注册完 Hook 之后，并不需要等待 Hook 执行完成，所以，这也算是一种 异步回调。
 * <p>
 * <p>
 * <p>
 * 扩展 多线程：
 * <pre>{@code
 *    static void runHooks() {
 *         Collection<Thread> threads;
 *         synchronized(ApplicationShutdownHooks.class) {
 *             threads = hooks.keySet();
 *             hooks = null;
 *         }
 *
 *         for (Thread hook : threads) {
 *             hook.start();
 *         }
 *         // 主线程 等待所有子线程执行完。
 *         for (Thread hook : threads) {
 *             while (true) {
 *                 try {
 *                     hook.join();
 *                     break;
 *                 } catch (InterruptedException ignored) {
 *                 }
 *             }
 *         }
 *     }
 * }</pre>
 * <p>
 * <p>
 * <p>
 * 模板模式 VS 回调
 * <p>
 * 从应用场景上来看，
 * “同步回调” 跟 “模板模式” 几乎一致。它们都是在一个大的算法骨架中，自由替换其中的某个步骤，起到代码复用和扩展的目的。
 * 而
 * “异步回调” 跟 “模板模式” 有较大差别，更像是 “观察者模式” 。
 * <p>
 * 从代码实现上来看，
 * ‘回调’ 和 ‘模板模式’ 完全不同：
 *     '回调' 基于 “组合关系” 来实现，把一个对象 '传递' 给 另一个对象，是一种 ‘对象’ 之间的关系；
 *     '模板模式' 基于 “继承关系” 来实现，子类 ‘重写’ 父类的抽象方法， 是一种 ‘类’   之间的关系。
 * <p>
 * 如何选择？
 *   前面我们也讲到，'组合' 优于 '继承'。
 *   实际上，这里也不例外。
 * <p>
 * 在代码实现上，'回调' 相对于 '模板模式' 会更加灵活，主要体现在下面几点：
 * <p>
 * 1.模板模式 子类不再拥有继承其他类的能力
 *   像 Java 这种只支持单继承的语言，基于模板模式编写的子类，已经继承了一个父类，不再具有继承其他类的能力。
 * <p>
 * 2.回调 可以使用 “匿名类” 来创建 “回调对象”，可以 不用事先定义类；
 *   而
 *   模板模式  针对不同的实现 都要 定义不同的子类。
 * <p>
 * 3.模板模式中，如果，某个类中定义了多个 ‘模板方法’ ，每个‘模板方法’都有对应的 ‘抽象方法’，
 *   那么，即便我们只用到其中的一个 ‘模板方法’，子类也必须实现所有的 ‘抽象方法’。
 *   而
 *   “回调” 就更加灵活，我们只需要往用到的 “模板方法” 中注入 “回调对象” 即可。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public class ShutdownHookDemo {

    /**
     * 这里的 hook 类似于 callback
     */
    private static class ShutdownHook extends Thread {
        @Override
        public void run() {
            System.out.println("I am called during shutting down.");
        }
    }

    // ApplicationShutdownHooks  类似于 被观察者。
    public static void main(String[] args) {

        // 注册一个 "JVM 关闭" 的 Hook
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());


        // 程序运行结束后，触发 Hook ; ApplicationShutdownHooks.runHooks()

    }

}
