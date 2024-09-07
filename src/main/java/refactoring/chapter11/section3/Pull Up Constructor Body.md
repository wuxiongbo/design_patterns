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
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
构造函数是很奇妙的东⻄。它们不是普通函数，使⽤它们⽐使⽤普通函数受到更多的限制。  

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
如果你看⻅各个⼦类中的函数有共同⾏为，第⼀个念头应该是：将共同⾏为提炼到⼀个独⽴函数中，然后，将这个函数提升到超类。  
对构造函数⽽⾔，它们彼此的共同⾏为往往就是“对象的建构”。  
这时候，你需要在超类中提供⼀个构造函数，然后让⼦类都来调⽤它。  
很多时候，⼦类构造函数 的唯⼀动作就是调⽤ 超类构造函数。  
这⾥不能运⽤ Pull Up Method（322），因为你⽆法在⼦类中继承超类构造函数。（你可曾痛恨过这个规定？） 

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
如果重构过程过于复杂，你可以考虑转⽽使⽤ Replace Constructor with Factory Method （304）。


## 做法
-[ ] 在超类中定义⼀个构造函数。 
-[ ] 将⼦类构造函数中的共同代码搬移到超类构造函数中。
>• 被搬移的可能是⼦类构造函数的全部内容.
 • ⾸先设法将共同代码搬移到⼦类构造函数起始处，然后再复制到超类构造函数中。
-[ ] 将⼦类构造函数中的共同代码删掉，改⽽调⽤新建的超类构造函数。
>• 如果⼦类构造函数中的所有代码都是⼀样的，那么⼦类构造函數就只需要 调⽤超类构造函数。
-[ ] 编译，测试。 
>• 如果⽇后⼦类构造函数再出现共同代码，你可以⾸先使⽤ Extract Method（110）将那⼀部分提炼到⼀个独⽴函数，
   然后，使⽤Pull Up Method（322）将该函数上移到超类。

