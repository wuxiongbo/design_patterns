# 字段上移
两个⼦类拥有相同的字段。 

将该宇段移⾄超类。

```plantuml
class Employee{}

class Salesman extends Employee{
    String name;
}


class Engineer extends Employee{
    String name;
}

```

```puml
class Employee{
    String name;
}
class Salesman extends Employee{}
class Engineer extends Employee{}
```

# 动机

如果各⼦类是分别开发的，或者是在重构过程中组合起来的，你常会发现它们拥有重复特性，特别是字段更容易重复。
这样的字段有时拥有近似的名字，但也并⾮绝对如此。  
判断若⼲字段是否重复，唯⼀的办法就是观察函数如何使⽤它们。
如果它们被使⽤的⽅式很相似，你就可以将它们归纳到超类去。  

本项重构从两⽅⾯减少重复：
⾸先, 它去除了重复的数据声明;
其次, 它使你可以将使⽤该字段的⾏为从⼦类移⾄超类，从⽽去除重复的⾏为。

# 做法

-[ ] 针对待提升之字段，检查它们的所有被使⽤点，确认它们以同样的⽅式被使⽤。

-[ ] 如果这些字段的名称不同，先将它们改名，使每⼀个名称都和你想为超类字 段取的名称相同。 

-[ ] 编译，测试。

-[ ] 在超类中新建⼀个字段。
> 如果这些字段是private的，你必须将超类的字段声明为 protected，这样，⼦类才能引⽤它

-[ ] 移除⼦类中的字段。

-[ ] 编译，測试。

-[ ] 考患对超类的新建字段使⽤Self Encapsulate Field （I71）。