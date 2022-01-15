package my_demo.abstractfactory.factory_method.concrete;

import my_demo.abstractfactory.bean.color.Color;
import my_demo.abstractfactory.bean.shape.Circle;
import my_demo.abstractfactory.bean.shape.Rectangle;
import my_demo.abstractfactory.bean.shape.Shape;
import my_demo.abstractfactory.bean.shape.Square;
import my_demo.abstractfactory.factory_method.AbstractFactory;

/**
 * <p>工厂</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}