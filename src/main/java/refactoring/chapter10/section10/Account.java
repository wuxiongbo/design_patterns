package refactoring.chapter10.section10;

/**
 * @author bear
 * @date 2024/8/3 下午7:45
 * @description
 */
public class Account {

    private final String _id;  // 译者注：这里的final修饰符必须去掉 ①

    Account(String id) {
        _id = initializeId(id);
    }

    private String initializeId(String id) {
        return "ZZ" + id;
    }

}
