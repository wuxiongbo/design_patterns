package my_demo.bridg.v2.additives;

/**
 * @author Xander Wu
 * @date 2022/9/27 18:02
 */
public class Sugar implements ICoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("加糖");
    }
}
