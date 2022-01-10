package chapter55;

/**
 * <p>享元模式</p>
 *
 *
 * Integer i = 59；底层执行了：Integer i = Integer.valueOf(59);
 *
 * int j = i; 底层执行了：int j = i.intValue();
 *
 *
 * 当我们通过“==”来判定两个对象是否相等的时候，实际上是在判断  两个局部变量存储的"地址"   是否相同，
 * 换句话说，是在判断两个局部变量是否指向相同的对象。
 *
 *
 * 前 4 行赋值语句都会触发自动装箱操作，也就是会创建 Integer 对象并且赋值给 i1、i2、i3、i4 这四个变量。
 * 根据刚刚的讲解，i1、i2 尽管存储的数值相同，都是 56，但是指向不同的 Integer 对象，所以通过“==”来判定是否相同的时候，会返回 false。
 * 同理，i3==i4 判定语句也会返回 false。
 * 不过，上面的分析还是不对，答案并非是两个 false，而是一个 true，一个 false。
 * 看到这里，你可能会比较纳闷了。
 * 实际上，这正是因为 Integer 用到了 “享元模式” 来复用对象，才导致了这样的运行结果。
 * 当我们通过自动装箱，也就是调用 valueOf() 来创建 Integer 对象的时候，
 * 如果要创建的 Integer 对象的值在 -128 到 127 之间，会从 IntegerCache 类中直接返回，
 * 否则才调用 new 方法创建。
 *
 * 具体代码如下：
 * public static Integer valueOf(int i) {
 *     if (i >= IntegerCache.low && i <= IntegerCache.high)
 *         return IntegerCache.cache[i + (-IntegerCache.low)];
 *     return new Integer(i);
 * }
 *
 * 实际上，这里的 IntegerCache 相当于，我们上一节课中讲的生成享元对象的工厂类，只不过名字不叫 xxxFactory 而已。
 * @see Integer.IntegerCache
 *
 *
 * 为什么 IntegerCache 只缓存 -128 到 127 之间的整型值呢？
 *
 * 因为，在 IntegerCache 的代码实现中，当这个类被加载的时候，缓存的享元对象会被集中一次性创建好。
 * 但是，毕竟整型值太多了，我们不可能在 IntegerCache 类中预先创建好所有的整型值，这样既占用太多内存，也使得加载 IntegerCache 类的时间过长。
 * 所以，我们只能选择缓存对于大部分应用来说 “最常用的整型值” ，也就是一个字节的大小（-128 到 127 之间的数据）。
 *
 * 实际上，JDK 也提供了方法来让我们可以自定义缓存的 "最大值" ，有下面两种方式:
 *
 * 方法一：
 *    -Djava.lang.Integer.IntegerCache.high=255
 * 方法二：
 *    -XX:AutoBoxCacheMax=255
 *
 * 如果你通过分析应用的 JVM 内存占用情况，发现 -128 到 255 之间的数据占用的内存比较多，你就可以用如下方式，将缓存的最大值从 127 调整到 255。
 *
 * 不过，这里注意一下，JDK 并没有提供设置最小值的方法。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class Demo {
    public static void main(String[] args){

        Integer i1 = 56;
        Integer i2 = 56;

        System.out.println(i1 == i2);


        Integer i3 = 129;
        Integer i4 = 129;

        System.out.println(i3 == i4);

    }
}
