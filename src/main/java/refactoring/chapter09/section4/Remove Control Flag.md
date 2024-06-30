Remove Control Flag（移除控制标记）

在⼀系列布尔表达式中，某个变量带有“控制标记”（control ﬂag）的作⽤。 

以break语句 或 return语句 取代控制标记。

动机

在⼀系列条件表达式中，你常常会看到⽤以判断何时停⽌条件检查的控制标记：

```shell
set done to false 
while not done
   if (condition)
       do something 
       set done to true 
   next step of loop
```

这样的控制标记带来的麻烦超过了它所带来的便利。
⼈们之所以会使⽤这样的控制标记，因为结构化编程原则告诉他们：每个 ⼦程序 只能有⼀个⼊口和⼀个出口。

我赞同“单⼀⼊⼝”原则（⽽且现代编程语⾔也强迫我们这样做），
但是“单⼀出⼝” 原则会让你在代码中加⼊讨厌的控制标记，⼤⼤降低条件表达式的可读性。

这就是 编程语⾔提供 break语句 和 continue语句 的原因：⽤它们跳出复杂的条件语句。
去掉控制标记所产⽣的效果往往让你⼤吃⼀惊：条件语句真正的⽤途会清晰得多。


## 做法

对控制标记的处理，最显⽽易⻅的办法就是使⽤ Java提供的 break语句 或 continue语句。

⼝ 找出让你跳出这段逻辑的控制标记值。 
⼝ 找出对标记变量赋值的语句，代以恰当的break语句或continue语句。 
⼝ 每次替换后，编译并测试。 在未能提供break和continue语句的编程语⾔中，可以使⽤下述办法。
⼝ 运⽤Extract Method （110），将壑段逻辑提炼到⼀个独⽴函数中。
⼝ 找出让你跳出这段逻辑的控制标记值。 
⼝ 找出对标记变量赋值的语句，代以恰当的return语句。 
⼝ 每次替换后，编译并测试。 
  
即使在⽀持break和continue语句的编程语⾔中，我通常也优先考虑上述第⼆⽅案。 
因为return语句可以⾮常清楚地表示：不再执⾏该函数中的其他任何代码。 如果还有这⼀类代码，你早晚需要将这段代码提炼出来。
请注意标记变量是否会影响这段逻辑的量后结果。
如果有影响，使⽤break语句之后还得保留控制标记值。
如果你已经将这段逻辑提炼成⼀个独⽴函数，也可以将控制标记值放在 return语句 中返回。


## 范例：以break取代简单的控制标记

下列函数⽤来检查⼀系列⼈名之中是否包含两个可疑⼈物的名字（这两个⼈的名字硬编码于代码中）：
```java
void checkSecurity(String[] people) {
    boolean found = false;
    for (int i = 0; i < people.length; i++) {
        if (!found) {
            if (people[i].equals("Don")) {
                sendAlert();
                found = true;
            }
            if (people[i].equals("John")) {
                sendAlert();
                found = true;
            }
        }
    }
}
```
这种情况下很容易找出控制标记：当变量 found 被赋予 true时，搜索就结束。 

我可以逐⼀引⼊break语句, 直到替换掉所有对found变量赋值的语句：：
```java
void checkSecurity(String[] people) {
    boolean found = false;
    for (int i = 0; i < people.length; i++) {
        if (!found) {
            if (people[i].equals("Don")) {
                sendAlert();
                break;
            }
            if (people[i].equals("John")) {
                sendAlert();
                break;
            }
        }
    }
}
```

然后就可以把所有对控制标记的引⽤都去掉：
```java
void checkSecurity(String[] people) {
    for (int i = 0; i < people.length; i++) {
        if (people[i].equals("Don")) {
            sendAlert();
            break;
        }
        if (people[i].equals("John")) {
            sendAlert();
            break;
        }
    }
}
```

范例：以 return 返回控制标记

本项重构的另⼀种形式将使⽤return语句。为了阐述这种⽤法，我把前⾯的例⼦稍加修改，以控制标记记录搜索结果：
```java
void checkSecurity(String[] people) {
    String found = "";
    for (int i = 0; i < people.length; i++) {
        if (found.equals("")) {
            if (people[i].equals("Don")) {
                sendAlert();
                found = "Don";
            }
            if (people[i].equals("John")) {
                sendAlert();
                found = "John";
            }
        }
    }
    someLaterCode(found);
}
```

在这⾥，变量found做了两件事：它既是控制标记，也是运算结果。
遇到这种情况，我喜欢先把计算 found变量的代码 提炼到⼀个独⽴函数中，
然后以return语句取代控制标记，
最后完全去掉控制标记：
```java
void checkSecurity(String[] people) {
    String found = foundMiscreant(people);
    someLaterCode(found);
}

String foundMiscreant(String[] people) {
    for (int i = 0; i < people.length; i++) {
        if (people[i].equals("Don")) {
            sendAlert();
            return "Don";
        }
        if (people[i].equals("John")) {
            sendAlert();
            return "John";
        }
    }
    return "";
}
```

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
即使不需要返回某值，也可以⽤return语句来取代控制标记。
这时候你只需要 ⼀个空的return语句就⾏了。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
当然，如果以此办法去处理带有副作⽤的函数，会有⼀些问题。
所以我需要先以 Separate Query from Modifier （279）将函数副作⽤分离出去。
稍后你会看到这⽅⾯的例子。