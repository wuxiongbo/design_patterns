package the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.impl;

import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.MsgSender;

import java.util.List;

/**
 * <p>邮件消息发送器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class EmailMsgSender implements MsgSender {

    private List<String> emailAddresses;

    public EmailMsgSender(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    @Override
    public void send(String message) {

    }
}
