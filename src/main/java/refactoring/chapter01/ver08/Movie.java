package refactoring.chapter01.ver08;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private final String _title;
    private int _priceCode;

    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public String getTitle() {
        return _title;
    }

    public void setPriceCode(int priceCode) {
        _priceCode = priceCode;
    }

    /**
     * 把因条件而异的代码替换掉。 条件中调用谁的属性判断，该代码就迁移给谁
     * <p>
     * 新需求： 用户准备修改影片分类规则
     * <p>
     * 一部影片可以在生命周期内修改自己的分类，一个对象却不能在生命周期内修改自己所属的类
     * <p>
     * 比如：
     * Movie newReleaseMovie= new NewReleaseMovie();
     * newReleaseMovie 的 type 是 new released
     * newReleaseMovie.getName() = "饥饿游戏3";
     *
     * 对象创建之后，任何时候调用 getCharge 都是调用 NewReleaseMovie 的 getCharge()方法。
     * 但是上映一段时间之后，影片的 type 改成了 regular movie了，（也就是对前半句话的解释）
     * 但是影片还是那个影片，即————实例化的对象没有变。
     * 实例化的对象没有变，就意味着 getCharge() 不变，这样就出现了问题。
     *
     * 这里的问题更具体的描述是：
     *     正在运行的 NewReleaseMovie 实例对象，不能修改 自己所属类的 getCharge() 方法。
     *
     *
     * @param daysRented
     * @return
     */
    public double getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()) {
            case Movie.REGULAR -> {
                result += 2;
                if (daysRented > 2)
                    result += (daysRented - 2) * 1.5;
            }
            case Movie.NEW_RELEASE -> result += daysRented * 3;
            case Movie.CHILDRENS -> {
                result += 1.5;
                if (daysRented > 3)
                    result += (daysRented - 3) * 1.5;
            }
        }
        return result;
    }

    /**
     * 把因条件而异的代码替换掉。 条件中调用谁的属性判断，该代码就迁移给谁
     *
     * @param daysRented
     * @return
     */
    public int getFrequentRenterPoints(int daysRented) {
        if ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
            return 2;
        else
            return 1;
    }
}