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
public class WordFile extends ResourceFile {
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        // 具体的 visit() 方法 进行的什么业务操作，由传入的 接口实现而定。
        // 利用多态的特性，避免了原本的accept方法重载
        visitor.visit(this);
    }

}