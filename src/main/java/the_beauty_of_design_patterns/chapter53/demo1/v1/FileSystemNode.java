package the_beauty_of_design_patterns.chapter53.demo1.v1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 递归遍历的文件系统目录树结构 </p>
 *
 * 需求：设计一个类，来表示文件系统中的  “目录” ，能方便地实现下面这些功能：
 *    1）动态地  添加、删除 某个目录下的 子目录 或 文件；
 *    2）统计 指定目录下的文件个数；
 *    3）统计 指定目录下的文件总大小。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
public class FileSystemNode {

    private String path;    // 路径

    private boolean isFile; // 是否为文件

    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }


    // 统计 指定目录下的文件个数
    public int countNumOfFiles() {
        if (isFile) {
            return 1;
        }
        int numOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            numOfFiles += fileOrDir.countNumOfFiles(); // 递归
        }
        return numOfFiles;
    }

    // 统计 指定目录下的文件总大小
    public long countSizeOfFiles() {
        if (isFile) {
            File file = new File(path);
            if (!file.exists()) {
                return 0;
            }
            return file.length();
        }
        long sizeofFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            sizeofFiles += fileOrDir.countSizeOfFiles(); // 递归
        }
        return sizeofFiles;
    }


    public String getPath() {
        return path;
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public void removeSubNode(FileSystemNode fileOrDir) {
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
