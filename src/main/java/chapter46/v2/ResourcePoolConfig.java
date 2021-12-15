package chapter46.v2;

import org.apache.commons.lang.StringUtils;

/**
 * <p> 建造者模式 </p>
 *
 * 我们可以把校验逻辑放置到 Builder 类中，先创建建造者，并且通过 set() 方法设置建造者的变量值，
 * 然后在使用 build() 方法真正创建对象之前，做集中的校验，校验通过之后才会创建对象。
 *
 *
 * 除此之外，我们把 ResourcePoolConfig 的构造函数改为 private 私有权限。
 * 这样我们就只能通过建造者来创建 ResourcePoolConfig 类对象。
 * 并且，ResourcePoolConfig 没有提供任何 set() 方法，这样我们创建出来的对象就是不可变对象了。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */

public class ResourcePoolConfig {

    // 属性仅在构造方法复制。 构建完成后，不可变，数据安全。
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    //...省略getter方法...


    // 将 构造方法 私有
    // 这样，我们就只能通过 Builder 来创建 ResourcePoolConfig 类对象。
    // 并且，ResourcePoolConfig 没有提供任何 set() 方法，这样我们创建出来的对象就是不可变对象了。
    private ResourcePoolConfig(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
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




    public static void main(String[] args){
        // 这段代码会抛出IllegalArgumentException，因为  minIdle > maxIdle
        ResourcePoolConfig config = new ResourcePoolConfig.Builder()
                .setName("dbconnectionpool")
                .setMaxTotal(16)
                .setMaxIdle(10)
                .setMinIdle(12)
                // 触发校验
                .build();
    }


    /**
     * 实际上，使用建造者模式创建对象，还能避免对象存在无效状态。
     * 我再举个例子解释一下。
     * 比如我们定义了一个长方形类，如果不使用建造者模式，采用先创建后 set 的方式，那就会导致在第一个 set 之后，对象处于无效状态。
     *
     * 为了避免这种无效状态的存在，我们就需要使用构造函数一次性初始化好所有的成员变量。
     * 如果构造函数参数过多，我们就需要考虑使用建造者模式，先设置建造者的变量，然后再一次性地创建对象，让对象一直处于有效状态。
     *
     * 实际上，如果我们并不是很关心对象是否有短暂的无效状态，也不是太在意对象是否是可变的。
     * 比如，对象只是用来映射数据库读出来的数据，那我们直接暴露 set() 方法来设置类的成员变量值是完全没问题的。
     * 而且，使用建造者模式来构建对象，代码实际上是有点重复的，ResourcePoolConfig 类中的成员变量，要在 Builder 类中重新再定义一遍。
     *
     * 具体代码如下所示：
     */
    public void test(){
        Rectangle r = new Rectangle(); // r is invalid
        r.setWidth(2); // r is invalid
        r.setHeight(3); // r is valid
    }
    
    private class Rectangle{
        public void setWidth(int i) {
        }
        public void setHeight(int i) {
        }
    }
}


