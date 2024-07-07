# 构造函数本体上移

你在各个⼦类中拥有⼀些构造函数，它们的本体⼏乎完全⼀致。 

在超类中新建⼀个构造函数，并在⼦类构造函数中调⽤它。

```java
class Employee {
    private String _name;
    private String _id;
    private int _grade;

    Employee(String name, String id, int grade) {
        _name = name;
        _id = id;
        _grade = grade;
    }
}
```

```java
class Manager extends Employee {
    private int _grade;

    Manager(String name, String id, int grade) {
        super(name, id);
        _grade = grade;
    }
}
```



## 动机
构造函数是很奇妙的东⻄。它们不是普通函数，使⽤它们⽐使⽤普通函数受到更多的限制。  
如果你看⻅各个⼦类中的函数有共同⾏为，第⼀个念头应该是将共同⾏为提炼到⼀个独⽴函数中，然后将这个函数提升到超类。  
对构造函数⽽⾔，它们彼此的共同⾏为往往就是“对象的建构”。  
这时候你需要在超类中提供⼀个构造函数，然后让⼦类都来调⽤它。  
很多时候，⼦类构造函数的唯⼀动作就是调⽤超类构造函数。  
这⾥不能运⽤Pull Up Method（322），因为你⽆法在⼦类中继承超类构造函数。（你可曾痛恨过这个规定？）  
如果重构过程过于复杂，你可以考虑转⽽使⽤ Replace Constructor with Factory Method （304）。


