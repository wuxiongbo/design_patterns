package design_patterns.chapter73.demo1.v0;

import design_patterns.chapter59.dependence.OnClickListener;
import design_patterns.chapter59.dependence.View;
import design_patterns.chapter73.dependence.Button;
import design_patterns.chapter73.dependence.Input;
import design_patterns.chapter73.dependence.Text;
import org.springframework.expression.spel.ast.Selection;

/**
 * <p> UI 控件  </p>
 *
 * 这个例子与 UI 控件有关，算是 中介模式 比较经典的应用，很多书籍在讲到中介模式的时候，都会拿它来举例。
 *
 * 假设我们有一个比较复杂的对话框，对话框中有很多控件，比如：按钮、文本框、下拉框 等。
 * 当我们对 某个控件 进行操作的时候，其他控件 会做出相应的反应，
 *      比如，
 *      我们在 下拉框中选择“注册”，注册 相关的控件就会显示在对话框中。
 *      如果我们在 下拉框中选择“登陆”，登陆 相关的控件就会显示在对话框中。
 *
 * 按照通常我们习惯的 UI 界面的开发方式，我们将刚刚的需求用代码实现出来，就是下面这个样子。
 * 在这种实现方式中，控件 和 控件 之间 互相操作、互相依赖。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class UIControl {

    private static final String LOGIN_BTN_ID = "login_btn";
    private static final String REG_BTN_ID = "reg_btn";
    private static final String USERNAME_INPUT_ID = "username_input";
    private static final String PASSWORD_INPUT_ID = "pswd_input";
    private static final String REPEATED_PASSWORD_INPUT_ID = "repeated_pswd_input";
    private static final String HINT_TEXT_ID = "hint_text";
    private static final String SELECTION_ID = "selection";


    public static void main(String[] args) {

        // 根据ID，显示不同的控件
        Button loginButton = (Button)findViewById(LOGIN_BTN_ID);
        Button regButton = (Button)findViewById(REG_BTN_ID);

        Input usernameInput = (Input)findViewById(USERNAME_INPUT_ID);
        Input passwordInput = (Input)findViewById(PASSWORD_INPUT_ID);
        Input repeatedPswdInput = (Input)findViewById(REPEATED_PASSWORD_INPUT_ID);
        Text hintText = (Text)findViewById(HINT_TEXT_ID);

        Selection selection = (Selection)findViewById(SELECTION_ID);




        // 给 ‘登录控件’  注册点击事件
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //  控件 需要与 其他控件 进行交互
                String username = usernameInput.text();
                String password = passwordInput.text();

                //校验数据...
                //做业务处理...
            }
        });


        // 给 ‘注册控件’  注册点击事件
        regButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //  控件 需要与 其他控件 进行交互
                // 获取usernameInput、passwordInput、repeatedPswdInput数据...
                usernameInput.show();
                passwordInput.show();
                repeatedPswdInput.hide();

                //校验数据...
                //做业务处理...
            }
        });


        //...省略selection下拉选择框相关代码....

    }


    static View findViewById(String viewId){
        return null;
    }
}
