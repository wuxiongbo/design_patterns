package chapter68.demo1.v4.visitor.concrete;

import chapter68.demo1.v4.resourcefile.concrete.PPTFile;
import chapter68.demo1.v4.resourcefile.concrete.PdfFile;
import chapter68.demo1.v4.resourcefile.concrete.WordFile;
import chapter68.demo1.v4.visitor.Visitor;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Extractor implements Visitor {

    // 方法重载
    @Override
    public void visit(PPTFile pptFile) {
        //...
        System.out.println(pptFile.hashCode()+"Extract PPT.");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        //...
        System.out.println(pdfFile.hashCode()+"Extract PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        //...
        System.out.println(wordFile.hashCode()+"Extract WORD.");
    }

}
