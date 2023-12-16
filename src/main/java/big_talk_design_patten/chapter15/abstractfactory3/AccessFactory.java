package big_talk_design_patten.chapter15.abstractfactory3;

//Access工厂
public class AccessFactory implements IFactory {

    public IUser createUser(){
        return new AccessUser();
    }

    public IDepartment createDepartment(){
        return new AccessDepartment();
    }

}


