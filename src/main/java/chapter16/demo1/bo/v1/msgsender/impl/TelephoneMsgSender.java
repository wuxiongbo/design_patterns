package chapter16.demo1.bo.v1.msgsender.impl;

import chapter16.demo1.bo.v1.msgsender.MsgSender;

import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class TelephoneMsgSender  implements MsgSender {

    private List<String> telephones;

    public TelephoneMsgSender(List<String> telephones) {
        this.telephones = telephones;
    }

    @Override
    public void send(String message) {
        //...
    }
}
