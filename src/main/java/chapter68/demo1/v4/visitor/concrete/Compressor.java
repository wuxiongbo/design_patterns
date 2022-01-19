package chapter68.demo1.v4.visitor.concrete;

import chapter68.demo1.v4.resourcefile.concrete.PPTFile;
import chapter68.demo1.v4.resourcefile.concrete.PdfFile;
import chapter68.demo1.v4.resourcefile.concrete.WordFile;
import chapter68.demo1.v4.visitor.Visitor;

/**
 * <p>压缩器</p>
 *
 *
 * compress  压缩
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Compressor implements Visitor {

    // 方法重载
    @Override
    public void visit(PPTFile pptFile) {
        //...
        System.out.println(pptFile.hashCode()+"compress PPT.");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        //...
        System.out.println(pdfFile.hashCode()+"compress PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        //...
        System.out.println(wordFile.hashCode()+"compress WORD.");
    }



}
