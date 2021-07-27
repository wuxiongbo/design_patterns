package chapter16.demo1.bo;

import lombok.Data;

@Data
/**
 * 给类中添加新的属性和方法，算作“修改”还是“扩展”？
 *
 * 我们再一块回忆一下开闭原则的定义：软件实体（模块、类、方法等）应该“对扩展开放、对修改关闭”。
 *
 * 从定义中，我们可以看出，开闭原则可以应用在不同粒度的代码中，可以是模块，也可以类，还可以是方法（及其属性）。
 * 同样一个代码改动，在粗代码粒度下，被认定为“修改”，在细代码粒度下，又可以被认定为“扩展”。
 *
 * 此处的改动一，添加属性和方法相当于修改类，
 *     在类这个层面，这个代码改动可以被认定为“修改”；
 *     但这个代码改动并没有修改已有的属性和方法，在方法（及其属性）这一层面，它又可以被认定为“扩展”。
 */
// 对方法的入参进行封装
public class ApiStatInfo {
    private String api;

    private long errorCount;

    // request 请求数量
    private long requestCount;
    // 持续的请求时间
    private long durationOfSeconds;

    private long timeoutCount; // 改动一：添加新字段
}
