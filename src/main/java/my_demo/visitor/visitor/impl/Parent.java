package my_demo.visitor.visitor.impl;

import my_demo.visitor.user.impl.Student;
import my_demo.visitor.user.impl.Teacher;
import my_demo.visitor.visitor.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 家长
 *  关注  学生班级、排名；教师级别
 * @author Xander Wu
 * @date 2022/11/3 11:20
 */
public class Parent implements Visitor {

    private Logger logger = LoggerFactory.getLogger(Parent.class);

    @Override
    public void visit(Student student) {
        logger.info("学生信息 姓名：{} 班级：{} 排名：{}", student.name, student.clazz,
                student.ranking());
    }

    @Override
    public void visit(Teacher teacher) {
        logger.info("老师信息 姓名：{} 班级：{} 级别：{}", teacher.name, teacher.clazz,
                teacher.identity);
    }

}