package chapter73.demo1.v1;

import chapter59.callback_demo2.OnClickListener;
import chapter59.dependence.View;
import chapter73.dependence.Button;
import chapter73.dependence.Input;
import chapter73.dependence.Selection;
import chapter73.dependence.Text;

/**
 * <p>描述类的信息</p>
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
