# 函数下移

超类中的某个函数只与部分（⽽⾮全部）⼦类有关。 


将这个函数移到相关的那些⼦类去。

```plantuml
class Employee {
    int getQuota()
}

salesman -up-|> Employee
engineer -up-|> Employee

```

```plantuml
class salesman {
    int getQuota();
}
salesman -up-|> Employee
engineer -up-|> Employee
```


# 动机

Push Down Method （328）与 Pull Up Method （322）恰恰相反。  
当我有必要把某些⾏为从 超类 移⾄ 特定的⼦类 时，我就使⽤ Push Down Method （328），
它通常也只在这种时候有⽤。  
使⽤ Extract Subclass （330）之后, 你可能会需要它。


做法

-[ ] 在所有⼦类中声明该函数，将超类中的函数本体复制到每⼀个⼦类函数中。 
> 你可能需要将超类的某些字段声明为protected，让⼦类函数也能够访问它们。   
> 如果⽇后你也想把这些字段下移到⼦类，通常就可以那么做；否则应 该使⽤超类提供的访问函数。   
> 如果访问函数并⾮public，你得将它声明为 protected.  

-[ ] 删除超类中的函数。 
> 你可能必须修改调⽤端的某些变量声明或参数声明，以便能够使⽤⼦类。  
> 如果有必要通过⼀个超类对象访问该函数，或你不想把该函数从任何⼦类中移除，再或超类是抽象类，
> 那么你就可以在超类中把该函数声明为抽象函数.
-[ ] 编译，测试。

-[ ] 将该函数从所有不需要它的那些⼦类中删掉。
-[ ] 编译，测试。

