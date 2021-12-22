package my_demo.decorator.dependence.item;

/**
 * <p>图书类别。 如，期刊、书本、报纸 等</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public abstract class LibraryItem {
    private String name;

    public LibraryItem(String name) {
        this.name = name;
    }

    public String getItemName() {
        return name;
    }

    public abstract String getType();
}
