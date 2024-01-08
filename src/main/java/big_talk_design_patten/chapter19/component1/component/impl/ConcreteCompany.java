package big_talk_design_patten.chapter19.component1.component.impl;

import big_talk_design_patten.chapter19.component1.component.Company;

import java.util.ArrayList;

/**
 * 具体分公司类，树枝节点
 *
 * @author bear
 */
public class ConcreteCompany extends Company {
    protected ArrayList<Company> children = new ArrayList<>();

    public ConcreteCompany(String name) {
        super(name);
    }

    @Override
    public void add(Company company) {
        children.add(company);
    }

    @Override
    public void remove(Company company) {
        children.remove(company);
    }

    @Override
    public void display(int depth) {
        for (var i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(name);
        for (Company item : children) {
            item.display(depth + 2);
        }
    }

    /**
     * 履行职责
     */
    @Override
    public void lineOfDuty() {
        for (Company item : children) {
            item.lineOfDuty();
        }
    }
}
