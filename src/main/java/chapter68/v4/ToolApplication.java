package chapter68.v4;

import chapter68.v4.resourcefile.ResourceFile;
import chapter68.v4.resourcefile.concrete.PPTFile;
import chapter68.v4.resourcefile.concrete.PdfFile;
import chapter68.v4.resourcefile.concrete.WordFile;
import chapter68.v4.visitor.concrete.Compressor;
import chapter68.v4.visitor.concrete.Extractor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class ToolApplication {

    public static void main(String[] args) {

        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);



        // 提取器 访问者
        Extractor extractorVisitor = new Extractor();
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(extractorVisitor);
        }


        // 压缩器 访问者
        Compressor compressorVisitor = new Compressor();
        for(ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(compressorVisitor);
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