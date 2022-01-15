package my_demo.abstractfactory.factory_method.concrete;

import my_demo.abstractfactory.bean.color.Blue;
import my_demo.abstractfactory.bean.color.Color;
import my_demo.abstractfactory.bean.color.Green;
import my_demo.abstractfactory.bean.color.Red;
import my_demo.abstractfactory.bean.shape.Shape;
import my_demo.abstractfactory.factory_method.AbstractFactory;

/**
 * <p>工厂</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
