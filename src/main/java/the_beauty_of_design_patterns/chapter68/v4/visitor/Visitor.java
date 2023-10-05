package the_beauty_of_design_patterns.chapter68.v4.visitor;

import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.PptFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.PdfFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.WordFile;

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
    void visit(PptFile pptFile);

    /**
     * 访问 word
     * @param wordFile
     */
    void visit(WordFile wordFile);
}
