package big_talk_design_patten.chapter19.component1.component;

/**
 * 公司抽象类
 *
 * @author bear
 */
public abstract class Company {

    protected String name;

    public Company(String name) {
        this.name = name;
    }

    /**
     * 增加
     *
     * @param company 公司
     */
    public abstract void add(Company company);

    /**
     * 移除
     *
     * @param company 公司
     */
    public abstract void remove(Company company);

    /**
     * 显示
     *
     * @param depth 深度
     */
    public abstract void display(int depth);

    /**
     * 履行职责
     */
    public abstract void lineOfDuty();

}
