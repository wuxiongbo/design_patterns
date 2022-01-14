package chapter51.demo4.external_system.targets;

/**
 * <p>外部系统A</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
public class A implements IA {
    //...
    @Override
    public void fa() {
        //...
        System.out.println("A.fa()");
    }
}