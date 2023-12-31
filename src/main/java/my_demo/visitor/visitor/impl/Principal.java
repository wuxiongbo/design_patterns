package my_demo.visitor.visitor.impl;

import my_demo.visitor.interviewee.impl.Student;
import my_demo.visitor.interviewee.impl.Teacher;
import my_demo.visitor.visitor.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 校长
 *  关注 学生 班级；教师 升本率
 * @author Xander Wu
 * @date 2022/11/3 11:19
 */
public class Principal implements Visitor {

    private final Logger logger = LoggerFactory.getLogger(Principal.class);

    @Override
    public void visit(Student student) {
        logger.info("学生信息 姓名：{} 班级：{}", student.name, student.clazz);
    }

    @Override
    public void visit(Teacher teacher) {
        logger.info("老师信息 姓名：{} 班级：{} 升学率：{}", teacher.name, teacher.clazz, teacher.entranceRatio());
    }

}
