package design_patterns.chapter46.v2;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * <p> 建造者模式 </p>
 *
 * Builder 模式，中文翻译为  建造者模式  或者  构建者模式，也有人叫它  生成器模式。
 *
 * 建造者模式的原理和代码实现非常简单，掌握起来并不难，难点在于 “应用场景”。
 *
 * 比如，你有没有考虑过这样几个问题：
 *      直接使用 构造函数 或者 配合set方法 就能创建对象，为什么还需要 '建造者模式' 来创建呢？
 *      建造者模式 和 工厂模式 都可以创建对象，那它们两个的区别在哪里呢？
 *
 * 优点：
 * 1）可以分散校验逻辑
 *   我们可以把校验逻辑放置到 Builder 类中，先创建建造者，并且通过 set() 方法设置建造者的变量值，
 *   然后在使用 build() 方法真正创建对象之前，做集中的校验，校验通过之后才会创建对象。
 *
 * 2）可以创建不可变对象
 *   除此之外，我们把 ResourcePoolConfig 的构造函数改为 private 私有权限。
 *   这样我们就只能通过建造者来创建 ResourcePoolConfig 类对象。
 *   并且，ResourcePoolConfig 没有提供任何 set() 方法，这样我们创建出来的对象就是不可变对象了。
 *
 * 3）避免 对象存在 “无效状态”
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */
@Getter
public class ResourcePoolConfig {

    // 属性仅在构造方法复制。 构建完成后不可变，数据安全。
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;


    // *** 将 构造方法 私有 ***
    // 这样，我们就只能通过 Builder 来创建 ResourcePoolConfig 类对象。
    // 并且，ResourcePoolConfig 没有提供任何 set() 方法，这样我们创建出来的对象就是不可变对象了。
    private ResourcePoolConfig(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }

    public static Builder builder(){
        return new Builder();
    }

    // 我们将Builder类设计成了 ResourcePoolConfig的内部类。
    // 也可以将Builder类设计成 独立的非内部类ResourcePoolConfigBuilder
    public static class Builder {
        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        //  使用建造者模式来构建对象，代码实际上是有点重复的，ResourcePoolConfig 类中的成员变量，要在 Builder 类中重新再定义一遍。
        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;


        // 在使用 build() 方法真正创建对象之前，做集中的校验，校验通过之后才会创建对象
        public ResourcePoolConfig build() {
            // 校验逻辑放到这里来做，包括 必填项校验、依赖关系校验、约束条件校验 等

            // 必填项校验
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }

            // 约束条件校验
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("...");
            }
            if (minIdle > maxTotal) {
                throw new IllegalArgumentException("...");
            }

            // 依赖关系校验
            if (minIdle > maxIdle) {
                throw new IllegalArgumentException("...");
            }

            return new ResourcePoolConfig(this);
        }


        public Builder setName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }
            this.name = name;
            return this;
        }
        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxTotal = maxTotal;
            return this;
        }
        public Builder setMaxIdle(int maxIdle) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxIdle = maxIdle;
            return this;
        }
        public Builder setMinIdle(int minIdle) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.minIdle = minIdle;
            return this;
        }
    }



    /**
     * 实际上，使用建造者模式创建对象，还能避免 对象存在 “无效状态”。
     * 举个例子解释一下：
     *     比如，
     *       我们定义了一个 长方形类 Rectangle，如果不使用建造者模式，采用 先创建 后 set 的方式，
     *       那就会导致在  set 宽width 之后，对象 处于“无效状态”。
     *
     * 为了避免这种无效状态的存在，我们就需要使用构造函数一次性初始化好所有的成员变量。
     * 如果构造函数参数过多，我们就需要考虑使用建造者模式，先设置建造者的变量，然后再一次性地创建对象，让对象一直处于有效状态。
     *
     * 不使用建造者模式的场景：
     *   1）如果我们并不是很关心对象是否有短暂的无效状态，也不是太在意对象是否是可变的。
     *      那么，我们直接暴露 set() 方法，来设置类的成员变量值是完全没问题的。
     *      比如，
     *        对象只是用来映射数据库读出来的数据。
     *   2）使用建造者模式来构建对象，代码实际上是有点重复的，
     *      如本示例所示，ResourcePoolConfig 类中的成员变量，要在 Builder 类中重新再定义一遍。
     *
     */
    public void test(){

        Rectangle r = new Rectangle(); // 无效
        r.setWidth(2); // 无效

        r.setHeight(3); // 有效
    }

    // 长方形
    @Data
    private static class Rectangle{
        private long width;  // 宽
        private long height; // 高
    }
}


