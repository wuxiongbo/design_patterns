# Separate Query from Modifier （将查询函数和修改函数分离）

某个函数既返回对象状态值，⼜修改对象状态。 

建⽴两个不同的函数，其中⼀个负责查询，另⼀个负责修改。

```puml
class Customer{
  getTotalOutstandingAndSetReady()
}
```

```puml
class Customer{
  getTotalOutstanding()
  setReady()
}
```



## 动机

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
如果某个函数只是向你提供⼀个值，没有任何看得到的副作⽤，那么这是个很有价值的东⻄。
你可以任意调⽤这个函数，也可以把调⽤动作搬到函数的其他地⽅。 
简⽽⾔之，需要操⼼的事情少多了。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
明确表现出 “有副作⽤” 与 “⽆副作⽤” 两种函数之间的差异，是个很好的想法。
下⾯是⼀条好规则：**任何有返回值的函数，都不应该有看得到的副作⽤。**
有些程序员甚⾄将此，作为⼀条必须遵守的规则［Meyer］。
就像对待任何东⻄⼀样，我并不绝对遵守它，不过我总是尽量遵守，⽽它也回报我很好的效果。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
如果你遇到⼀个“既有 返回值，⼜有 副作⽤”的函数，就应该试着将查询动作从修改动作中分割出来。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
你也许已经注意到了：我使⽤“看得到的副作⽤”这种说法。
有⼀种常⻅的优化办法是：将查询所得结果缓存于某个字段中，这么⼀来后续的重复查询就可以⼤⼤加快速度。
虽然这种做法改变了对象的状态，但这⼀修改是察觉不到的，因为不论如何查询，你总是获得相同结果［Meyer］。


## 做法

⼝ 新建⼀个查询函数，令它返回的值与原函数相同。
•观察原函数，看它返回什么东⻄。如果返回的是⼀个临时变量，找出临时 变量的位置。
⼝修改原函数，令它调⽤查询函数，并返回获得的结果。 
•原函数中的每个return句都应该像这样：return newQuery（），⽽不 应该返回其他东⻄。 
 如果调⽤者将返回值赋给了⼀个临时变量，你应该能够去除这个临时变量。 
⼝编译，测试。 
⼝ 将调⽤原函数的代码改为调⽤查询函数。然后，在调⽤查询函数的那⼀⾏之 前，加上对原函数的调⽤。每次修改后，编译并测试。
⼝ 将原函数的返回值改为void，并删掉其中所有的return语句。



## 范例

有这样⼀个函数：⼀旦有⼈⼊侵安全系统，它会告诉我⼊侵者的名字，并发送 ⼀个警报。
如果⼊侵者不⽌⼀个，也只发送⼀条警报：
```java
String foundMiscreant(String[] people){
  for (int i = 0; i < people.length; i++){
    if (people[i].equals("Don")){
      sendAlert();
      return "Don";
    }
    if (people[i].equals("John")){
      sendAlert();
      return "John";
    }
  }
  return "";
}
```

该函数被下列代码调⽤：
```java
void checkSecurity(String[] people){
  String found = foundMiscreant(people);
  someLaterCode(found);
}
```
为了将查询动作和修改动作分开，我⾸先建⽴⼀个适当的查询函数，使其与修 改函数返回相同的值，但不造成任何副作⽤：
```java
String foundPerson(String[] people){
  for (int i = 0; i < people.length; i++){
    if (people[i].equals("Don")){
      return "Don";
    }
    if (people[i].equals("John")){
      return "John";
    }
  }
  return "";
}
```

然后，我要逐⼀替换原函数内所有的return语句，改调⽤新建的查询函数。 每次替换后，编译并测试。这⼀步完成之后，原函数如下所示：
```java
String foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
        if (people[i].equals("Don")) {
            sendAlert();
            return foundPerson(people);
        }
        if (people[i].equals("John")) {
            sendAlert();
            return foundPerson(people);
        }
    }
    return foundPerson(people);
}
```

现在，我要修改调⽤者，将原本的单⼀调⽤动作替换为两个调⽤：先调⽤修改 函数，然后调⽤查询函数：
```java
void checkSecurity(String[] people){
    foundMiscreant(people);
    String found = foundPerson(people);
    someLaterCode(found);
}
```
所有调⽤都替换完毕后，我就可以将修改函数的返回值改为void：
```java
void foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
        if (people[i].equals("Don")) {
            sendAlert();
            return;
        }
        if (people[i].equals("John")) {
            sendAlert();
            return;
        }
    }
}
```

现在，为原函数改个名称可能会更好⼀些：
```java
void sendAlert(String[] people) {
    for (int i = 0; i < people.length; i++) {
        if (people[i].equals("Don")) {
            sendAlert();
            return;
        }
        if (people[i].equals("John")) {
            sendAlert();
            return;
        }
    }
}
```

当然，这种情况下，我得到了⼤量重复代码，因为修改函数之中使⽤了与查询函数相同的代码。
现在我可以对修改函数实施 Substitute Algorithm （139），设法让它再简洁⼀些：
```java
void sendAlert(String[] people) {
    if (!foundPerson(people).equals(""))
        sendAlert();
}
    
```

## 并发问题

如果你在⼀个多线程系统中⼯作，肯定知道这样⼀个重要的惯⽤⼿法：在同⼀个动作中完成 检查 和 赋值。
这是否和 Separate Query from Modifier（279）互相⽭盾呢？
我曾经和 Doug Lea 讨论过这个问题，并得出结论：
两者并不⽭盾，但你需要做⼀些额外⼯作。
将 查询动作 和 修改动作 分开来，仍然是很有价值的。
但你需要保留 第三个函数 来同时做这两件事。
这个 “查询-修改” 函数将调⽤各⾃独⽴的查询函数和修改函数，并被声明为 synchronized。
如果 查询函数 和 修改函数 未被声明为 synchronized，那么你还应该将它们的可⻅范围限制在 包级别 或 private级别。
这样，你就可以拥有⼀个安全、同步的操作，它由两个较易理解的函数组成。
这两个较低层函数也可以⽤于其他场合。
