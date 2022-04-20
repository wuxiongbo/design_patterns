package my_demo.facade.colleague.items;

import java.util.Random;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 22:39
 * @Description:
 */
public class Tax {
    /**
     * 收取多少税金
     *
     * @return
     */
    public int getTax() {
        return (new Random()).nextInt(300);
    }
}
