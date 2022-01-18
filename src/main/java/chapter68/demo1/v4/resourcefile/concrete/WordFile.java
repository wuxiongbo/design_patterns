package chapter68.demo1.v4.resourcefile.concrete;

import chapter68.demo1.v4.visitor.Visitor;
import chapter68.demo1.v4.visitor.concrete.Compressor;
import chapter68.demo1.v4.visitor.concrete.Extractor;
import chapter68.demo1.v4.resourcefile.ResourceFile;

/**
 * <p>描述类的信息</p>
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
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

}