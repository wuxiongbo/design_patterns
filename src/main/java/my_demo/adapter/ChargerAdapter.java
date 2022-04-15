package my_demo.adapter;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public abstract class ChargerAdapter implements ITarget{
    @Override
    public int Voltage220V() {
        return 0;
    }
    @Override
    public int Voltage110V() {
        return 0;
    }
    @Override
    public int Voltage30V() {
        return 0;
    }
    @Override
    public int Voltage5V() {
        return 0;
    }
}
