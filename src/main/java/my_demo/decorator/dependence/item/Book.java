package my_demo.decorator.dependence.item;

/**
 * <p>书本</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Book extends LibraryItem {

    private String type = "Book";

    public Book(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return type;
    }
}
