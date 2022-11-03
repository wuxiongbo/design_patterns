package chapter68.v4.visitor;

import chapter68.v4.resourcefile.concrete.PPTFile;
import chapter68.v4.resourcefile.concrete.PdfFile;
import chapter68.v4.resourcefile.concrete.WordFile;

/**
 * <p>访问者</p>
 *
 * visit()方法 重载
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public interface Visitor {

    // visit 访问

    /**
     * 访问 pdf
     * @param pdfFile
     */
    void visit(PdfFile pdfFile);

    /**
     * 访问 ppt
     * @param pptFile
     */
    void visit(PPTFile pptFile);

    /**
     * 访问 word
     * @param wordFile
     */
    void visit(WordFile wordFile);
}
