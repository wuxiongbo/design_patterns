package design_patterns.chapter68.v1;

import design_patterns.chapter68.v1.resourcefile.concrete.PPTFile;
import design_patterns.chapter68.v1.resourcefile.concrete.PdfFile;
import design_patterns.chapter68.v1.resourcefile.concrete.WordFile;

/**
 * <p>提取器</p>
 *
 * 尝试 抽取  “业务操作” 这部分逻辑，实现 "业务" 与 "数据"  解耦
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Extractor {

    // 将 对资源文件的  抽取文本、压缩、提取文件元信息  等业务操作，抽取到 此类当中，实现业务解耦

    public void extract2txt(PPTFile pptFile) {
        //...省略一大坨从PPT中抽取文本的代码...
        System.out.println("Extract PPT.");
    }

    public void extract2txt(PdfFile pdfFile) {
        //...省略一大坨从PDF中抽取文本的代码...
        System.out.println("Extract PDF.");
    }

    public void extract2txt(WordFile wordFile) {
        //...省略一大坨从WORD中抽取文本的代码...
        System.out.println("Extract WORD.");
    }
}
