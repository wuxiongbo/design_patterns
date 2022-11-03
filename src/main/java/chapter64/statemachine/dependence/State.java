package chapter64.statemachine.dependence;

/**
 * <p>描述类的信息</p>
 *
 * 马里奥可以变身为多种形态，
 *      小 马里奥（Small Mario）、
 *      超级 马里奥（Super Mario）、
 *
 *      火焰 马里奥（Fire Mario）、
 *      斗篷 马里奥（Cape Mario）
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public enum State {

    SMALL(0),
    SUPER(1),

    FIRE(2),
    CAPE(3);

    private int value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
