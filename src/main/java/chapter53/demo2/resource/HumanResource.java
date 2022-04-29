package chapter53.demo2.resource;

/**
 * <p>人力资源</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
public abstract class HumanResource {

    protected long id;
    protected double salary; // 薪水

    public HumanResource(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract double calculateSalary();
}