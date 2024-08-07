# 以对象取代数组
你有⼀个数组，其中的元素各⾃代表不同的东⻄。 

以对象替換数组。对于数组中的每个元素，以⼀个字段来表示。

```java
public static void main(String[] args) {
    String[] row = new String[3];
    row[0] = "Liverpool";
    row[1] = "15";
}
```

```java
public static void main(String[] args) {
    Performance row = new Performance();
    row.setName("Liverpool");
    row.setWins("15");
}
```

## 动机

数组是⼀种常⻅的⽤以组织数据的结构。不过，它们应该只⽤于“以某种顺序容纳⼀组相似对象”。
有时候你会发现，⼀个数组容纳了多种不同对象，这会给⽤户带来麻烦，因为他们很难记住像 “数组的第⼀个元素是⼈名” 这样的约定。
对象就不同了，你可以运⽤ 字段名称 和 函数名称 来传达这样的信息，因此你⽆需死记它， 也⽆需依赖注释。
⽽且如果使⽤对象，你还可以将信息封装起来，并使⽤Move Method （142）为它加上相关⾏为。


## 做法

-[ ] 新建⼀个类表示数组所拥有的信息，并在其中以⼀个public字段保存原先的数组。
-[ ] 修改数组的所有⽤户，让它们改⽤新类的实例。 
-[ ] 编译，测试。 
-[ ] 逐⼀为数组元素添加取值/设值函数。根据元素的⽤途，为这些访问函数命名。
   修改客户端代码，让它们通过访问函数取⽤数组内的元素。每次修改后， 编译并测试。
-[ ] 当所有对数组的直接访问都转⽽调⽤访问函数后，将新类中保存该数组的字段声明为private。
-[ ] 编译。
-[ ] 对于数组内的每⼀个元素，在新类中创建⼀个类型相当的字段。修改该元素 的访问函数，令它改⽤上述的新建字段。
-[ ] 每修改⼀个元素，编译并测试。 
-[ ] 数组的所有元素都有了相应字段之后，删除该数组。

## 范例

我们的范例从⼀个数组开始，其中有3个元素，分别保存 ⼀⽀球队的 名称、获胜场次 和 失利场次。这个数组的声明可能像这样：