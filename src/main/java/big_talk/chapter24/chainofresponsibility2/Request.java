package big_talk.chapter24.chainofresponsibility2;

import lombok.Getter;
import lombok.Setter;
public class Request {
    //申请类别
    @Getter
    @Setter
    private String requestType;
    //申请内容
    @Getter
    @Setter
    private String requestContent;
    //数量
    @Getter
    @Setter
    private int number;
}

//管理者抽象类

