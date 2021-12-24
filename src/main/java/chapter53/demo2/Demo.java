package chapter53.demo2;

import chapter53.demo2.depencence.DepartmentDao;
import chapter53.demo2.depencence.EmployeeDao;
import chapter53.demo2.resource.impl.Department;
import chapter53.demo2.resource.impl.Employee;

import java.util.List;

/**
 * <p>描述类的信息</p>
 *  我们再拿组合模式的定义跟这个例子对照一下：
 *      “将 一组对象（员工和部门） 组织成  树形结构，以表示一种 ‘部分-整体’ 的层次结构（部门与子部门的嵌套结构）。
 *        组合模式让 客户端 可以统一   单个对象（员工） 和  组合对象（部门）  的处理逻辑（递归遍历）。”
 *
 *
 *  组合模式的设计思路，与其说是一种设计模式，倒不如说是对业务场景的一种数据结构和算法的抽象。
 *  其中，数据可以表示成树这种数据结构，业务需求可以通过在树上的递归遍历算法来实现。
 *
 *
 *
 *  组合模式，将一组对象组织成树形结构，将 单个对象 和 组合对象 都看做 树中的节点，以统一处理逻辑，
 *  并且，它利用树形结构的特点，递归地处理每个子树，依次简化代码实现。
 *
 *
 *  使用组合模式的前提在于，你的业务场景必须能够表示成树形结构。
 *  所以，组合模式的应用场景也比较局限，它并不是一种很常用的设计模式。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
// 构建组织架构的代码
public class Demo {
    private static final long ORGANIZATION_ROOT_ID = 1001;

    private DepartmentDao departmentDao; // 依赖注入
    private EmployeeDao employeeDao; // 依赖注入

    public void buildOrganization() {
        Department rootDepartment = new Department(ORGANIZATION_ROOT_ID);
        buildOrganization(rootDepartment);
    }

    private void buildOrganization(Department department) {

        // 部门
        List<Long> subDepartmentIds = departmentDao.getSubDepartmentIds(department.getId());
        for (Long subDepartmentId : subDepartmentIds) {
            Department subDepartment = new Department(subDepartmentId);
            department.addSubNode(subDepartment);
            buildOrganization(subDepartment);
        }

        // 部门员工
        List<Long> employeeIds = employeeDao.getDepartmentEmployeeIds(department.getId());
        for (Long employeeId : employeeIds) {

            double salary = employeeDao.getEmployeeSalary(employeeId);

            department.addSubNode(new Employee(employeeId, salary));
        }

    }
}
