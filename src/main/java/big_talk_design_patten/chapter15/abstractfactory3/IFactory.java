package big_talk_design_patten.chapter15.abstractfactory3;

//工厂接口
public interface IFactory {

    public IUser createUser();

    public IDepartment createDepartment();
    
}

