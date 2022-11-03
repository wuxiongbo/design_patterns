package my_demo.visitor.visitor;

import my_demo.visitor.user.Student;
import my_demo.visitor.user.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xander Wu
 * @date 2022/11/3 11:19
 */
public class Principal implements Visitor {

    private Logger logger = LoggerFactory.getLogger(Principal.class);

    @Override
    public void visit(Student student) {
        logger.info("学生信息 姓名：{} 班级：{}", student.name, student.clazz);
    }

    @Override
    public void visit(Teacher teacher) {
        logger.info("老师信息 姓名：{} 班级：{} 升学率：{}", teacher.name, teacher.clazz, teacher.entranceRatio());
    }

}
