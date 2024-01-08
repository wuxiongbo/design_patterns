package the_beauty_of_design_patterns.chapter53.demo1.v2.node.impl;

import the_beauty_of_design_patterns.chapter53.demo1.v2.node.AbstractFileSystemNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件夹</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
public class Directory extends AbstractFileSystemNode {

    // 子节点
    private List<AbstractFileSystemNode> subNodes = new ArrayList<>();

    public Directory(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        int numOfFiles = 0;
        for (AbstractFileSystemNode fileOrDir : subNodes) {
            numOfFiles += fileOrDir.countNumOfFiles();
        }
        return numOfFiles;
    }

    @Override
    public long countSizeOfFiles() {
        long sizeofFiles = 0;
        for (AbstractFileSystemNode fileOrDir : subNodes) {
            sizeofFiles += fileOrDir.countSizeOfFiles();
        }
        return sizeofFiles;
    }

    public void addSubNode(AbstractFileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public void removeSubNode(AbstractFileSystemNode fileOrDir) {
        int size = subNodes.size();
        int i = 0;
        for (; i < size; ++i) {
            if (subNodes.get(i).getPath().equalsIgnoreCase(fileOrDir.getPath())) {
                break;
            }
        }
        if (i < size) {
            subNodes.remove(i);
        }
    }
}