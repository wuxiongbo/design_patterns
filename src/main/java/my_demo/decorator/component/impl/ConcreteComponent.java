package my_demo.decorator.component.impl;

import my_demo.decorator.component.Component;

/**
 * <p>具体组件</p>
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
