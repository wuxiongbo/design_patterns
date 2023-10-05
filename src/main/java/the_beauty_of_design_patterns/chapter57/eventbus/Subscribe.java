package the_beauty_of_design_patterns.chapter57.eventbus;

import com.google.common.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 1.Subscribe </p>
 *
 * Subscribe 是一个注解，用于标明 观察者中的 哪个函数 可以接收消息。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Beta
public @interface Subscribe {}
