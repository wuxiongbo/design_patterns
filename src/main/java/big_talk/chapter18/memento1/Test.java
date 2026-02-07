package big_talk.chapter18.memento1;

public class Test {
	
	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        //大战Boss前
        GameRole role = new GameRole();
        role.getInitState();
        role.displayState();

        //保存进度
        GameRole backup = new GameRole();
        backup.setVitality(role.getVitality());
        backup.setAttack(role.getAttack());
        backup.setDefense(role.getDefense());

        //大战Boss时，损耗严重
        role.fight();
        //显示状态
        role.displayState();

        //游戏进度恢复
        role.setVitality(backup.getVitality());
        role.setAttack(backup.getAttack());
        role.setDefense(backup.getDefense());

        //显示状态
        role.displayState();

		System.out.println();
		System.out.println("**********************************************");

	}
}

//游戏角色类

