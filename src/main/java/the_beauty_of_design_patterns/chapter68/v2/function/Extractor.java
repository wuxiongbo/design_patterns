package the_beauty_of_design_patterns.chapter68.v2.function;

import the_beauty_of_design_patterns.chapter68.v2.resourcefile.concrete.PPTFile;
import the_beauty_of_design_patterns.chapter68.v2.resourcefile.concrete.PdfFile;
import the_beauty_of_design_patterns.chapter68.v2.resourcefile.concrete.WordFile;

/**
 * <p>提取器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Extractor {

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
