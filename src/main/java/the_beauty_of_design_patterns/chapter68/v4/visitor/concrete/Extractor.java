package the_beauty_of_design_patterns.chapter68.v4.visitor.concrete;

import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.PptFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.PdfFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.WordFile;
import the_beauty_of_design_patterns.chapter68.v4.visitor.Visitor;

/**
 * <p>提取器</p>
 *
 * 观察者的视角之一
 *
 * 不同观察者，对目标对象的关注点不一样。 如：此处关注点是  对目标对象的提取逻辑
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Extractor implements Visitor {

    // 方法重载
    @Override
    public void visit(PptFile pptFile) {
        //...ppt文本提取实现
        System.out.println(pptFile.hashCode()+"Extract PPT.");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        //...pdf文本提取实现
        System.out.println(pdfFile.hashCode()+"Extract PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        //...word文本提取实现
        System.out.println(wordFile.hashCode()+"Extract WORD.");
    }

}
