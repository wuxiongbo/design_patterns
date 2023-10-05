package the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.impl;

import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.MsgSender;

import java.util.List;

/**
 * <p>短信消息发送器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class TelephoneMsgSender  implements MsgSender {

    // 待通知的手机号
    private List<String> telephones;

    public TelephoneMsgSender(List<String> telephones) {
        this.telephones = telephones;
    }

    @Override
    public void send(String message) {
        //...
    }
}
