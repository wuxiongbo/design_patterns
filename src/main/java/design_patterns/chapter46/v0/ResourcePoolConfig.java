package design_patterns.chapter46.v0;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * <p> 建造者模式 </p>
 *
 * 设计面试题：
 *
 *    我们需要定义一个资源池配置类 ResourcePoolConfig。（这里的资源池，你可以简单理解为 线程池、连接池、对象池 等）
 *    在这个 资源池配置类 中，有以下几个成员变量（也就是可配置项）：
 *         name、 maxTotal 、maxIdle、 minIdle
 *
 *    现在，请你编写代码，实现这个 ResourcePoolConfig 类。
 *
 * 分析：
 *    只要你稍微有点开发经验，那实现这样一个类对你来说并不是件难事。
 *    最常见、最容易想到的实现思路如下代码所示。
 *    因为 maxTotal、maxIdle、minIdle 不是必填变量，
 *    所以在创建 ResourcePoolConfig 对象的时候，我们通过往构造函数中，给这几个参数传递 null 值，来表示使用默认值。
 *
 * 问题：
 *  1）如果可配置项逐渐增多，变成了 8 个、10 个，甚至更多，
 *    那继续沿用现在的设计思路，构造函数的参数列表会变得很长，代码在可读性和易用性上都会变差。
 *  2）在使用构造函数的时候，我们容易搞错各参数的顺序，传递进错误的参数值，导致非常隐蔽的 bug。
 *     ResourcePoolConfig config =
 *           new ResourcePoolConfig("dbconnectionpool", 16, null, 8, null, false , true, 10, 20, false,  true);
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */
@Data
public class ResourcePoolConfig {
    // 常量
    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;


    // 配置项
    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;



    // maxTotal、maxIdle、minIdle 不是必填变量，
    // 所以, 在创建 ResourcePoolConfig 对象的时候，我们通过往构造函数中，给这几个参数传递 null 值，来表示使用默认值。
    public ResourcePoolConfig(String name, Integer maxTotal, Integer maxIdle, Integer minIdle) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name should not be empty.");
        }
        this.name = name;

        if (maxTotal != null) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("maxTotal should be positive.");
            }
            this.maxTotal = maxTotal;
        }

        if (maxIdle != null) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("maxIdle should not be negative.");
            }
            this.maxIdle = maxIdle;
        }

        if (minIdle != null) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("minIdle should not be negative.");
            }
            this.minIdle = minIdle;
        }
    }


}