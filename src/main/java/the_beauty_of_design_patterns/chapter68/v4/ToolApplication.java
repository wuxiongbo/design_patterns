package the_beauty_of_design_patterns.chapter68.v4;

import the_beauty_of_design_patterns.chapter68.v4.resourcefile.ResourceFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.PptFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.PdfFile;
import the_beauty_of_design_patterns.chapter68.v4.resourcefile.concrete.WordFile;
import the_beauty_of_design_patterns.chapter68.v4.visitor.Visitor;
import the_beauty_of_design_patterns.chapter68.v4.visitor.concrete.Compressor;
import the_beauty_of_design_patterns.chapter68.v4.visitor.concrete.Extractor;

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

    // 1) 资源文件
    private final List<ResourceFile> resourceFiles = new ArrayList<>();

    public void attach( ResourceFile resourceFile){
        resourceFiles.add(resourceFile);
    }

    public void operate(Visitor tool) {
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(tool);
        }
    }





}