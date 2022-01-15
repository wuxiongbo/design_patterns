package my_demo.abstractfactory.factory_method;

import my_demo.abstractfactory.bean.color.Color;
import my_demo.abstractfactory.bean.shape.Shape;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public abstract class AbstractFactory {

    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);

}
