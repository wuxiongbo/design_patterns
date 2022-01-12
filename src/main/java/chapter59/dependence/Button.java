package chapter59.dependence;

import chapter59.callback_demo2.OnClickListener;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public interface Button {

    void setOnClickListener(OnClickListener onClickListener);

}
