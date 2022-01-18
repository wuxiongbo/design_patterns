package chapter68.demo1.v2;

import chapter68.demo1.v2.resourcefile.ResourceFile;
import chapter68.demo1.v2.resourcefile.concrete.PPTFile;
import chapter68.demo1.v2.resourcefile.concrete.PdfFile;
import chapter68.demo1.v2.resourcefile.concrete.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 *
 * 在执行resourceFile.accept(extractor);行的时候
 * 根据多态特性，程序会调用实际类型的 accept 函数，
 * 比如 PdfFile 的 accept 函数，也就是 extractor.extract2txt(this); 这行代码
 * 而 这行代码中的 this 类型是 PdfFile 的，这在编译的时候就已经确定了，所以会调用 extractor 中的 extract2txt(PdfFile pdfFile)，这个重载函数。
 *
 * 这个实现思路是不是很有技巧？
 *
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

        Extractor extractor = new Extractor();

        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);

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