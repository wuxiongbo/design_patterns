package chapter73.demo1.v1;

import chapter73.dependence.Button;
import chapter73.dependence.Component;
import chapter73.dependence.Input;
import chapter73.dependence.Selection;
import chapter73.dependence.Text;
import lombok.Data;

/**
 * <p>中介 实现</p>
 *
 * 中介模式
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
@Data
public class LandingPageDialog implements Mediator {
    private Button loginButton;
    private Button regButton;
    private Selection selection;
    private Input usernameInput;
    private Input passwordInput;
    private Input repeatedPswdInput;
    private Text hintText;

    @Override
    public void handleEvent(Component component, String event) {

        // 登录控件
        if (component.equals(loginButton)) {
            String username = usernameInput.text();
            String password = passwordInput.text();
            //校验数据...
            //做业务处理...
        }

        // 注册控件
        else if (component.equals(regButton)) {
            //获取usernameInput、passwordInput、repeatedPswdInput数据...
            //校验数据...
            //做业务处理...
        }

        // 下拉框控件
        else if (component.equals(selection)) {
            String selectedItem = selection.select();
            if (selectedItem.equals("login")) {
                usernameInput.show();
                passwordInput.show();
                repeatedPswdInput.hide();
                hintText.hide();
                //...省略其他代码
            } else if (selectedItem.equals("register")) {
                //....
            }
        }

    }
}