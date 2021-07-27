import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 *  EasyExcel
 */
public class testdemo {

    private static final Logger LOGGER = LogManager.getLogger(testdemo.class);

    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "D:\\标准成本-工单关闭成本侧进行归集数据校验和归集并自动结算-内网SIT.xls";

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName).extraRead(CellExtraTypeEnum.MERGE).sheet().doRead();

    }

    @Test
    public void test1(){
        //        HashMap<Integer,String> data = new HashMap<>();
//        data.put(1,"A");
//        data.put(2,"B");
//        data.put(3,"A");
//        data.put(4,"B");
//        data.put(5,"C");
//        data.put(6,"p");
//
//        HashMap<String, Set<Integer>> map = new HashMap<>();
//
//        for (Map.Entry<Integer, String> entry : data.entrySet()) {
//            Integer k= entry.getKey();
//            String v = entry.getValue();
//            Set<Integer> temp = map.get(v);
//            if(temp==null){
//                Set<Integer> a = new HashSet<>();
//                a.add(k);
//                map.put(v,a);
//            }else{
//                temp.add(k);
//            }
//        }

    }

}
