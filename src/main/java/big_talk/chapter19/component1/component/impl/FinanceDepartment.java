package big_talk.chapter19.component1.component.impl;

import big_talk.chapter19.component1.component.Company;

/**
 * 财务部，树叶节点
 * @author bear
 */
public class FinanceDepartment extends Company {
    public FinanceDepartment(String name) {
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
        System.out.println(name + " 公司财务收支管理");
    }

}
