package chapter68.v0;

import chapter68.v0.resourcefile.concrete.PPTFile;
import chapter68.v0.resourcefile.concrete.PdfFile;
import chapter68.v0.resourcefile.ResourceFile;
import chapter68.v0.resourcefile.concrete.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 * 示例： 文本信息提取工具。
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

        // 在 ToolApplication 中，利用 “多态” 特性，根据 对象 “运行时”的 实际类型，来决定执行哪个方法。
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.extract2txt();
        }

    }








    /**
     * 获取资源文件
     * @param resourceDirectory
     * @return
     */
    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {

        List<ResourceFile> resourceFiles = new ArrayList<>();

        // 工厂方法
        // ...根据 后缀(pdf/ppt/word)，创建不同的类对象(PdfFile/PPTFile/WordFile)

        resourceFiles.add(new PdfFile(resourceDirectory+"a.pdf"));
        resourceFiles.add(new WordFile(resourceDirectory+"b.word"));
        resourceFiles.add(new PPTFile(resourceDirectory+"c.ppt"));

        return resourceFiles;

    }
}