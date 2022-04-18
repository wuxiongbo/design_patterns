package my_demo.adapter;

/**
 * <p>  接口适配器模式 / 缺省适配器模式  </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public abstract class ChargerAdapter implements ITarget {
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
