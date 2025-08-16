package design_patterns.chapter68.v3.function;

import design_patterns.chapter68.v3.resourcefile.concrete.PPTFile;
import design_patterns.chapter68.v3.resourcefile.concrete.PdfFile;
import design_patterns.chapter68.v3.resourcefile.concrete.WordFile;

/**
 * <p>提取器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Extractor {

    // extract2txt 方法  方法重载
    public void extract2txt(PPTFile pptFile) {
        //...
        System.out.println(pptFile.hashCode()+"Extract PPT.");
    }

    public void extract2txt(PdfFile pdfFile) {
        //...
        System.out.println(pdfFile.hashCode()+"Extract PDF.");
    }

    public void extract2txt(WordFile wordFile) {
        //...
        System.out.println(wordFile.hashCode()+"Extract WORD.");
    }

}
