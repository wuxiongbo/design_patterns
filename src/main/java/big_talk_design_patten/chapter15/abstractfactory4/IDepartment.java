package big_talk_design_patten.chapter15.abstractfactory4;

//部门类接口
public interface IDepartment {

    public void insert(Department department);

    public Department getDepartment(int id);
}
