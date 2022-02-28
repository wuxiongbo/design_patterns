package chapter7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>购物车</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/20
 * </pre>
 */

public class ShoppingCart {

    private int itemsCount;
    private double totalPrice;

    private List<ShoppingCartItem> items = new ArrayList<>();


    public int getItemsCount() {
        return this.itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // items 属性的 getter 方法，返回的是一个 List集合容器。
    // 外部调用者在拿到这个容器之后，可以操作容器内部数据，也就是说，外部代码还是能修改 items 中的数据。
    public List<ShoppingCartItem> getItems() {
        return this.items;
    }

    public void addItem(ShoppingCartItem item) {
        items.add(item);
        itemsCount++;
        totalPrice += item.getPrice();
    }
    // ...省略其他方法...


    /**
     * 正确的做法应该是，在 ShoppingCart 类中定义一个 clear() 方法，将清空购物车的业务逻辑封装在里面，透明地给调用者使用。
     * <p>
     * 避免 itemsCount、totalPrice、items 三者数据不一致。
     */
    public void clear() {
        items.clear();
        itemsCount = 0;
        totalPrice = 0.0;
    }

    /**
     * 我们可以通过 Java 提供的 Collections.unmodifiableList() 方法，让 getter 方法返回一个不可被修改的 UnmodifiableList 集合容器
     * @return
     */
    public List getItems1() {
        return Collections.unmodifiableList(this.items);
    }


    /**
     * 虽然我们没法修改容器中的数据，但我们仍然可以修改容器中每个对象（ShoppingCartItem）的数据。
     *
     * 通过 原型模式 解决这个问题
     * @param args
     */
    public static void main(String[] args){

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCartItem());


        List<ShoppingCartItem> items = cart.getItems();
        ShoppingCartItem item = items.get(0);
        item.setPrice(19.0); // 这里修改了item的价格属性

    }
}
