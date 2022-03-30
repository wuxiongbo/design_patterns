package chapter68.v4.visitor;

import chapter68.v4.resourcefile.concrete.PPTFile;
import chapter68.v4.resourcefile.concrete.PdfFile;
import chapter68.v4.resourcefile.concrete.WordFile;

/**
 * <p>访问者</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public interface Visitor {

    // visit 访问

    /**
     * @param pdfFile
     */
    void visit(PdfFile pdfFile);

    /**
     * @param pdfFile
     */
    void visit(PPTFile pdfFile);

    /**
     * @param pdfFile
     */
    void visit(WordFile pdfFile);
}
