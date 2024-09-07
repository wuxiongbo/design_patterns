# 字段下移
超类中的某个字段只被部分（⽽⾮全部）⼦类⽤到。 

将这个字段移到需要它的那些⼦类去。

```puml
class Employee{
   String quota
}

class Salesman extends Employee{}
class Engineer extends Employee{}
```

```puml
class Employee{}

class Salesman extends Employee{
   String quota
}
class Engineer extends Employee{}

```



# 动机
Push Down Field （329）与 Pull Up Field （320）恰恰相反：
如果只有某些（⽽⾮全部）⼦类 需要 超类 内的⼀个字段，你可以使⽤本项重构。


# 做法
-[ ] 在所有⼦类中声明该字段。
-[ ] 将该字段从超类中移除。
-[ ] 编译，测试。
-[ ] 将该字段从所有不需要它的那些⼦类中删掉。
-[ ] 编译，测试。
