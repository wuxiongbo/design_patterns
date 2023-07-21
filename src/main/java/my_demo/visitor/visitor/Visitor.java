package my_demo.visitor.visitor;

import my_demo.visitor.user.impl.Student;
import my_demo.visitor.user.impl.Teacher;

/**
 * 访问者
 * @author Xander Wu
 * @date 2022/11/3 11:19
 */
public interface Visitor {

    // 访问学生信息
    void visit(Student student);

    // 访问老师信息
    void visit(Teacher teacher);

}