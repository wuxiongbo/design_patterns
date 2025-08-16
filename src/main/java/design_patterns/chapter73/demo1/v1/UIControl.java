package design_patterns.chapter73.demo1.v1;

import design_patterns.chapter59.dependence.OnClickListener;
import design_patterns.chapter59.dependence.View;
import design_patterns.chapter73.dependence.Button;
import design_patterns.chapter73.dependence.Input;
import design_patterns.chapter73.dependence.Selection;
import design_patterns.chapter73.dependence.Text;

/**
 * <p>  UI 控件  中介模式 </p>
 *
 * 从代码中我们可以看出，原本业务逻辑会分散在各个控件中，现在都集中到了中介类中。
 *
 * 实际上，这样做既有好处，也有坏处。
 *
 * 好处是
 *      简化了控件之间的交互，
 *
 * 坏处是
 *      中介类有可能会变成大而复杂的“上帝类”（God Class）。
 *
 * 所以，在使用中介模式的时候，我们要根据实际的情况，平衡对象之间交互的复杂度和中介类本身的复杂度。
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
        Button loginButton = (Button)findViewById(LOGIN_BTN_ID);
        Button regButton = (Button)findViewById(REG_BTN_ID);
        Input usernameInput = (Input)findViewById(USERNAME_INPUT_ID);
        Input passwordInput = (Input)findViewById(PASSWORD_INPUT_ID);
        Input repeatedPswdInput = (Input)findViewById(REPEATED_PASSWORD_INPUT_ID);
        Text hintText = (Text)findViewById(HINT_TEXT_ID);
        Selection selection = (Selection)findViewById(SELECTION_ID);

        LandingPageDialog dialog = new LandingPageDialog();
        dialog.setLoginButton(loginButton);
        dialog.setRegButton(regButton);
        dialog.setUsernameInput(usernameInput);
        dialog.setPasswordInput(passwordInput);
        dialog.setRepeatedPswdInput(repeatedPswdInput);
        dialog.setHintText(hintText);
        dialog.setSelection(selection);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 控件 只与 中介交互
                dialog.handleEvent(loginButton, "click");
            }
        });

        regButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 控件 只与 中介交互
                dialog.handleEvent(regButton, "click");
            }
        });

        //....
    }


    static View findViewById(String viewId){
        return null;
    }

}
