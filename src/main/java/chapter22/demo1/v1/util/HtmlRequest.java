package chapter22.demo1.v1.util;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class HtmlRequest {
    String address;
    Byte[] data;

    Content content;

    public HtmlRequest(String url) {
    }

    public String getAddress() {
        return null;
    }

    public class Content{
        public Byte[] getBytes() {
            return data;
        }
    }

    public Content getContent() {
        return null;
    }




}
