package chapter59.callback_demo2;

import chapter59.dependence.Button;
import chapter59.dependence.R;
import chapter59.dependence.View;

/**
 * <p>异步回调</p>
 *
 * 在客户端开发中，我们经常给控件注册事件监听器，比如下面这段代码，就是
 * 在 Android 应用开发中，给 Button 控件的点击事件注册监听器。
 *
 *
 * 从代码结构上来看，事件监听器 很像回调，即传递一个包含回调函数（onClick()）的对象给另一个函数。
 *
 * 从应用场景上来看，它又很像  观察者模式，即 事先注册 观察者（OnClickListener），
 * 当用户点击按钮的时候，发送点击事件给观察者，并且执行相应的 onClick() 函数。
 *
 *
 * 我们前面讲到，回调分为 同步回调 和 异步回调。
 * 这里的回调算是异步回调，我们往 setOnClickListener() 函数中注册好回调函数之后，并不需要等待回调函数执行。
 * 这也印证了我们前面讲的，异步回调 比较像 观察者模式。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public class Demo {

    public static void main(String[] args){


        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(
                // 传入 “回调对象”
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("I am clicked.");
                    }
                }

        );

        // 继续后面的逻辑，
        // 这里的回调算是 “异步回调” ，


        // 我们往 setOnClickListener() 函数中注册好回调函数之后，并不需要等待回调函数执行。
        // 这也印证了我们前面讲的，异步回调 比较像 观察者模式。

    }


    static Button findViewById(Button button){
        return null;
    }

}
