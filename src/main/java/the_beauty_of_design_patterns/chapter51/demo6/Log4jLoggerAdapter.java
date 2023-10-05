package the_beauty_of_design_patterns.chapter51.demo6;

import org.apache.log4j.Priority;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import java.io.Serializable;

/**
 * <p>  常见的四种结构型模式 对比 </p>
 *
 * 代理、桥接、装饰器、适配器，这 4 种模式是比较常用的结构型设计模式。它们的代码结构非常相似。
 * 笼统来说，它们都可以称为 Wrapper 模式，也就是通过 Wrapper类  二次封装 原始类。
 *
 * 区别
 *      代理模式：代理模式在 ‘不改变原始类接口’ 的条件下，为原始类定义一个代理类，
 *               主要目的是  “控制访问”，即，对原有的类的功能 进行 ‘修改’ 、 ‘替换’ 甚至 ‘拦截’ ，原来的方法可能不起作用。
 *               而非 原有类功能的 “加强功能” ，这是它跟  装饰器模式  最大的不同。
 *
 *      桥接模式：桥接模式的目的是，将 接口部分 和 实现部分 ‘分离’，从而让它们可以较为容易、也相对独立地加以改变。
 *
 *      装饰器模式：装饰者模式在 ‘不改变原始类接口’ 的情况下，对原始类功能进行 ‘增强’ ，并且支持多个装饰器的嵌套使用。
 *
 *      适配器模式：适配器模式是一种事后的 补救策略。
 *               适配器提供 “跟原始类不同的接口” ，而 代理模式、装饰器模式 提供的都是 “跟原始类相同的接口”。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
// 将 log4j 适配为 slf4j
//
// log4j日志框架的适配器
// Log4jLoggerAdapter实现了LocationAwareLogger接口，其中LocationAwareLogger继承自Logger接口，
// 相当于Log4jLoggerAdapter实现了Logger接口。
public final class Log4jLoggerAdapter extends MarkerIgnoringBase
        implements LocationAwareLogger, Serializable {

    final transient org.apache.log4j.Logger logger; // log4j

    public Log4jLoggerAdapter(org.apache.log4j.Logger logger){
        this.logger = logger;
    }


    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        logger.log("FQCN", Priority.DEBUG, msg, null);
    }

    @Override
    public void debug(String format, Object arg) {
        // 委托给 log4j 实现
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.log("FQCN", Priority.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        // 委托给 log4j 实现
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            logger.log("FQCN", Priority.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object[] argArray) {
        // 委托给 log4j 实现
        if (logger.isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logger.log("FQCN", Priority.DEBUG, ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        // 委托给 log4j 实现
        logger.log("FQCN", Priority.DEBUG, msg, t);
    }





    //...省略一堆接口的实现...

    @Override
    public boolean isTraceEnabled() {
        return false;
    }
    @Override
    public void trace(String s) {
    }
    @Override
    public void trace(String s, Object o) {
    }
    @Override
    public void trace(String s, Object o, Object o1) {
    }
    @Override
    public void trace(String s, Object... objects) {
    }
    @Override
    public void trace(String s, Throwable throwable) {
    }
    @Override
    public boolean isInfoEnabled() {
        return false;
    }
    @Override
    public void info(String s) {
    }
    @Override
    public void info(String s, Object o) {
    }
    @Override
    public void info(String s, Object o, Object o1) {
    }
    @Override
    public void info(String s, Object... objects) {
    }
    @Override
    public void info(String s, Throwable throwable) {
    }
    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void warn(String s) {

    }

    @Override
    public void warn(String s, Object o) {

    }

    @Override
    public void warn(String s, Object... objects) {

    }

    @Override
    public void warn(String s, Object o, Object o1) {

    }

    @Override
    public void warn(String s, Throwable throwable) {

    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public void error(String s) {

    }

    @Override
    public void error(String s, Object o) {

    }

    @Override
    public void error(String s, Object o, Object o1) {

    }

    @Override
    public void error(String s, Object... objects) {

    }

    @Override
    public void error(String s, Throwable throwable) {

    }

    @Override
    public void log(Marker marker, String s, int i, String s1, Object[] objects, Throwable throwable) {

    }

}
