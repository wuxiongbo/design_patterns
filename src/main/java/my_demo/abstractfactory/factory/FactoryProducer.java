package my_demo.abstractfactory.factory;

import my_demo.abstractfactory.factory_method.AbstractFactory;
import my_demo.abstractfactory.factory_method.concrete.ColorFactory;
import my_demo.abstractfactory.factory_method.concrete.ShapeFactory;

/**
 * <p>工厂的工厂</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }

}
