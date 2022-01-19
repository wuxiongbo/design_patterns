package chapter68.v4.resourcefile.concrete;

import chapter68.v4.visitor.Visitor;
import chapter68.v4.resourcefile.ResourceFile;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class PdfFile extends ResourceFile {
    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

}
