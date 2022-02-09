package chapter68.v1;

import chapter68.v1.resourcefile.ResourceFile;
import chapter68.v1.resourcefile.concrete.PPTFile;
import chapter68.v1.resourcefile.concrete.PdfFile;
import chapter68.v1.resourcefile.concrete.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 * 访问者模式 的演进
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

        // 尝试 通过方法重载 实现扩展
        for (ResourceFile resourceFile : resourceFiles) {
            // 编译不通过。
//            extractor.extract2txt(resourceFile);
        }

    }

    // 资源文件列表
    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {

        List<ResourceFile> resourceFiles = new ArrayList<>();

        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)

        resourceFiles.add(new PdfFile(resourceDirectory+"a.pdf"));
        resourceFiles.add(new WordFile(resourceDirectory+"b.word"));
        resourceFiles.add(new PPTFile(resourceDirectory+"c.ppt"));

        return resourceFiles;

    }
}