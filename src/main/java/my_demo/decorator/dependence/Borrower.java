package my_demo.decorator.dependence;

/**
 * <p> 借书人 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Borrower {
    private final String name;

    public Borrower(String name) {
        this.name = name;
    }

    public String getUserName() {
        return name;
    }
}
