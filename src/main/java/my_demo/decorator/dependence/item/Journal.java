package my_demo.decorator.dependence.item;

/**
 * <p>杂志、期刊</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Journal extends LibraryItem {

    private String type = "Journal";

    public Journal(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return type;
    }
}