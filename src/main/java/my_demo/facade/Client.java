package my_demo.facade;

import java.util.Date;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 22:41
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        // 定义门面
        HRFacade facade = new HRFacade();


        System.out.println("===外系统查询总收入===");
        int salary = facade.querySalary("张三", new Date(System.currentTimeMillis()));
        System.out.println("张三 本月 总收入为：" + salary);


        System.out.println("===外系统查询出勤天数===");
        int workDays = facade.queryWorkDays("李四");
        System.out.println("李四 本月出勤：" + workDays);
    }
}
