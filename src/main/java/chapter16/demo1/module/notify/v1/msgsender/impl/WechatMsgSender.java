package chapter16.demo1.module.notify.v1.msgsender.impl;

import chapter16.demo1.module.notify.v1.msgsender.MsgSender;

import java.util.List;

/**
 * <p>微信消息发送器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class WechatMsgSender implements MsgSender {

    private List<String> wechatIds;

    public WechatMsgSender(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    @Override
    public void send(String message) {

    }
}