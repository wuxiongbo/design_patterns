package big_talk.chapter28.visitor2;

import big_talk.chapter28.visitor2.person.Man;
import big_talk.chapter28.visitor2.person.Person;
import big_talk.chapter28.visitor2.person.Woman;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println(); 

        ArrayList<Person> persons = new ArrayList<>();

        Person man1 = new Man();
        man1.setAction("成功");
        persons.add(man1);

        Person woman1 = new Woman();
        woman1.setAction("成功");
        persons.add(woman1);



        Person man2 = new Man();
        man2.setAction("失败");
        persons.add(man2);

        Person woman2 = new Woman();
        woman2.setAction("失败");
        persons.add(woman2);



        Person man3 = new Man();
        man3.setAction("恋爱");
        persons.add(man3);

        Person woman3 = new Woman();
        woman3.setAction("恋爱");
        persons.add(woman3);

        for(Person item : persons) {
            item.getConclusion();
        }

        System.out.println();
        System.out.println("**********************************************");
    }
}

