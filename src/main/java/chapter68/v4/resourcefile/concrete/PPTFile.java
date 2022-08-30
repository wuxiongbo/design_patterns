package chapter68.v4.resourcefile.concrete;

import chapter68.v4.visitor.Visitor;
import chapter68.v4.resourcefile.ResourceFile;

/**
 * <p>资源文件</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class PPTFile extends ResourceFile {
    public PPTFile(String filePath) {
        super(filePath);
    }

    // 利用 多态特性，避免了原本的accept方法重载
    @Override
    public void accept(Visitor visitor) {
        // 具体的 visit() 方法， 进行的什么业务操作，由传入的 访问者实现 而定。
        visitor.visit(this);
    }

}
