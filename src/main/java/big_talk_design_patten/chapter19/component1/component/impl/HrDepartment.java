package big_talk_design_patten.chapter19.component1.component.impl;

import big_talk_design_patten.chapter19.component1.component.Company;

/**
 * 人力资源部，树叶节点
 * @author bear
 */
public class HrDepartment extends Company {
    public HrDepartment(String name) {
        super(name);
    }

    @Override
    public void add(Company company) {
    }

    @Override
    public void remove(Company company) {
    }

    @Override
    public void display(int depth) {
        for (var i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(name);
    }

    /**
     * 履行职责
     */
    @Override
    public void lineOfDuty() {
        System.out.println(name + " 员工招聘培训管理");
    }
}
