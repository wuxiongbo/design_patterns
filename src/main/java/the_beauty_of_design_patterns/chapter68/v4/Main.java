package the_beauty_of_design_patterns.chapter68.v4;

import the_beauty_of_design_patterns.chapter68.v4.resourcefile.ResourceFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.PdfFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.PptFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.WordFile;
import the_beauty_of_design_patterns.chapter68.v4.visitor.Visitor;
import the_beauty_of_design_patterns.chapter68.v4.visitor.concrete.Compressor;
import the_beauty_of_design_patterns.chapter68.v4.visitor.concrete.Extractor;

import java.util.ArrayList;
import java.util.List;

/**
 * 不容易变化的部分: 文件类型
 *
 * 容易变化的部分: 对文件的功能操作, 压缩/提取/转换 等等;  这部分是访问者.
 *
 * 将来添加新功能,实现访问者就行了.
 */
public class Main {
    public static void main(String[] args) {
        ToolApplication toolApplication = new ToolApplication();
        listAllResourceFiles(args[0]).forEach(toolApplication::attach);

        // 扩展: 提取器——访问者1
        Visitor extractor = new Extractor();
        toolApplication.operate(extractor);

        // 扩展: 压缩器——访问者2
        Visitor compressor = new Compressor();
        toolApplication.operate(compressor);
    }


    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {

        List<ResourceFile> resourceFiles = new ArrayList<>();

        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)

        resourceFiles.add(new PdfFile(resourceDirectory + "a.pdf"));
        resourceFiles.add(new WordFile(resourceDirectory + "b.word"));
        resourceFiles.add(new PptFile(resourceDirectory + "c.ppt"));

        return resourceFiles;

    }

}
