package the_beauty_of_design_patterns.chapter69.polymorphism;

/**
 * <p>子类</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class ChildClass extends ParentClass {
    @Override
    public void f() {
        System.out.println("I am ChildClass's f().");
    }
}