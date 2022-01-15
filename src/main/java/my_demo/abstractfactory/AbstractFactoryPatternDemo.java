package my_demo.abstractfactory;

import my_demo.abstractfactory.bean.color.Color;
import my_demo.abstractfactory.bean.shape.Shape;
import my_demo.abstractfactory.factory_method.AbstractFactory;
import my_demo.abstractfactory.factory.FactoryProducer;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {

        testShape();
        testColor();

    }

    static void testShape() {

        //获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");



        //获取形状为 Circle 的对象
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        //获取形状为 Rectangle 的对象
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        //获取形状为 Square 的对象
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }


    static void testColor() {

        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");



        //获取颜色为 Red 的对象
        Color color1 = colorFactory.getColor("RED");
        color1.fill();

        //获取颜色为 Green 的对象
        Color color2 = colorFactory.getColor("Green");
        color2.fill();

        //获取颜色为 Blue 的对象
        Color color3 = colorFactory.getColor("BLUE");
        color3.fill();
    }

}