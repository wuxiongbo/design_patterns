package the_beauty_of_design_patterns.chapter68.v2;

import the_beauty_of_design_patterns.chapter68.v2.function.Extractor;
import the_beauty_of_design_patterns.chapter68.v2.resourcefile.ResourceFile;
import the_beauty_of_design_patterns.chapter68.v2.resourcefile.concrete.PPTFile;
import the_beauty_of_design_patterns.chapter68.v2.resourcefile.concrete.PdfFile;
import the_beauty_of_design_patterns.chapter68.v2.resourcefile.concrete.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 *
 * 在执行  resourceFile.accept(extractor);  这一行代码的时候，
 * 根据 ‘多态特性’ ，程序会调用实际类型的 accept 函数，
 * 比如 PdfFile 的 accept 函数，也就是 extractor.extract2txt(this); 这行代码
 *
 * 而 这行代码中的 this类型是 PdfFile 的，这在编译的时候就已经确定了，
 * 所以，会调用 extractor 中的 extract2txt(PdfFile pdfFile)，这个重载函数。
 *
 *
 * 这个实现思路是不是很有技巧？
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class ToolApplication {

    public static void main(String[] args) {

        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);

        // 我们 转换思路， 将  ‘提取器’  注入给  ‘资源文件’。 而不是 直接向 ‘资源文件’ 传入到 ‘提取器’
        // 注意：
        //    这里的反转，是设计的精妙之处
        Extractor extractor = new Extractor();
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(extractor);
        }

    }









    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {

        List<ResourceFile> resourceFiles = new ArrayList<>();

        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)

        resourceFiles.add(new PdfFile(resourceDirectory+"a.pdf"));
        resourceFiles.add(new WordFile(resourceDirectory+"b.word"));
        resourceFiles.add(new PPTFile(resourceDirectory+"c.ppt"));

        return resourceFiles;

    }
}