package refactoring.chapter08.section3.v2;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * 1)⾸先我使⽤Replace Constructor with Factory Method （304)。这样，我就可以控制customer对象的创建过程，这在以后会是⾮常重要的。
 * 我在Customer类中定义这 个⼯⼚函数：
 * <p>
 * 2)现在，我必须决定如何访问Customer对象。我⽐较喜欢通过另⼀个对象（例 如order中的⼀个字段)来访问它。
 * 但是本例并没有这样⼀个明显的字段可⽤于访问customer对象。
 * 在这种情况下，我通常会创建⼀个注册表对象来保存所有customer对象，以此作为访问点。
 * 为了简化我们的例⼦，我把这个注册表保存在Customer类的static字段中，让Customer类作为访问点：
 *
 * 然后我得决定：应该在接到请求时创建新的customer对象，还是应该预先将它们创建好。
 * 这⾥我选择后者。
 * 在应⽤程序的启动代码中，我先把需要使⽤的 customer对象加载妥当。
 * 这些对象可能来⾃数据库，也可能来⾃⽂件。
 * 为求简单起⻅，我在代码中明确⽣成这些对象。
 * 反正以后我总是可以使⽤ Substitute Algorithm（139）来改变它们的创建⽅式。
 *
 * @author bear
 * @date 2024/4/4 21:19
 * @description
 */
public class Customer {

    private static final Dictionary<String, Customer> INSTANCES = new Hashtable<>();

    private final String _name;

    private Customer(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    // 加载一次
    static void loadCustomers() {
        new Customer("Lemon Car Hire").store();
        new Customer("Associated Coffee Machines ").store();
        new Customer("Bilston Gasworks ").store();
    }

    private void store() {
        INSTANCES.put(this.getName(), this);
    }

    /**
     * 由于create() 总是返回既有的Customer对象，所以, 我应该使⽤Rename Method （273）修改这个⼯⼚函数的名称，以便强调这⼀点。
     * @param name name
     * @return Customer
     */
    public static Customer getNamed(String name) {
        return INSTANCES.get(name);
    }

}
