package my_demo.decorator.demo1.component.impl;

import my_demo.decorator.demo1.component.Component;

/**
 * <p> 具体组件： 被装饰类对象</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ConcreteComponent extends Component {

    @Override
    public void operation(){
        System.out.println("ConcreteComponent say");
    }

}
