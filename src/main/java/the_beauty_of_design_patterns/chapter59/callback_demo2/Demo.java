package the_beauty_of_design_patterns.chapter59.callback_demo2;

import the_beauty_of_design_patterns.chapter59.dependence.Button;
import the_beauty_of_design_patterns.chapter59.dependence.OnClickListener;
import the_beauty_of_design_patterns.chapter59.dependence.R;
import the_beauty_of_design_patterns.chapter59.dependence.View;

/**
 * <p>异步回调</p>
 *
 * 在客户端开发中，我们经常给控件注册 “事件监听器” ，比如下面这段代码就是：
 * 在 Android 应用开发中，给 Button 控件（被观察者）  的 "点击事件" 注册 '监听器'(观察者)。
 *
 *
 * 从代码结构上来看，“事件监听器” 很像 ‘回调’ 。
 * 即————传递一个 包含  回调函数（onClick()） 的  '匿名对象'  给 另一个函数。
 *
 * 从应用场景上来看，它又很像  “观察者模式”  ，即 事先注册  ‘观察者’（OnClickListener），
 * 当 用户点击（触发条件） 按钮（被观察者） 的时候，发送  点击事件（Event） 给 ‘观察者’，‘观察者’ 执行相应的 onClick() 函数。
 *
 *
 * 我们前面讲到，回调 分为： 同步回调 和 异步回调。
 * 这里的 回调 算是  “异步回调”  ，我们往 setOnClickListener() 函数中 注册好 回调函数 之后，并不需要等待回调函数执行。
 *
 * 这也印证了我们前面讲的，“异步回调” 比较像 “观察者模式”。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public class Demo {

    public static void main(String[] args){


        Button button = (Button)findViewById(R.id.button);



        // 不用等回调结束，继续后面的逻辑。
        // 这里的 回调 算是 “异步回调” ，
        button.setOnClickListener(
                // 传入 “回调对象”
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("I am clicked.");
                    }
                }

        );





        // 我们往 setOnClickListener() 函数中注册好回调函数之后，并不需要等待回调函数执行。
        // 这也印证了我们前面讲的，异步回调 比较像 “观察者模式”  。

    }


    static Button findViewById(Button button){
        return null;
    }

}
