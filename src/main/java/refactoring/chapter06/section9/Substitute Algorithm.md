# 替换算法

你想要把某个算法替换为另⼀个更清晰的算法。

```java
String foundPerson(String[] people) {
    for (int i = 0; i < people.length; i++) {
        if (people[i].equals("Don")) {
            return "Don";
        }
        if (people[i].equals("John")) {
            return "John";
        }
        if (peoplel[i].equals("Kent")) {
            return "Kent";
        }
    }
    return "";
}
```

将函数本体替換为另⼀个算法。
```java
String foundPerson(String[] people) {
    List candidates = Arrays.asList(new String[] {"Don", "John", "Kent"});
    for (int i = 0; i < people.length; i++) {
        if (candidates.contains(people[i])) {
            return people[i];
        }
    }
    return "";
}
```
## 动机
&emsp;&emsp;&emsp;&emsp; 解决问题有好⼏种⽅法，我敢打赌其中某些⽅法会⽐另⼀些简单。算法也是如此。
如果你发现做⼀件事可以有更清晰的⽅式，就应该以较清晰的⽅式取代复杂的⽅式。
“重构”可以把⼀些复杂东⻄分解为较简单的⼩块，但有时你就是必须壮⼠断腕，删掉整个算法，代之以较简单的算法。
随着对问题有了更多理解，你往往会发现，在原先的做法之外，有更简单的解决⽅案，此时你就需要改变原先的算法。
如果你开始使⽤程序库，⽽其中提供的某些功能/特性与你⾃⼰的代码重复，那么你也需要改变原先的算法。  
&emsp;&emsp;&emsp;&emsp; 有时候你会想要修改原先的算法，让它去做⼀件与原先略有差异的事。
这时候你也可以先把原先的算法替换为⼀个较易修改的算法，这样后续的修改会轻松许多。  
&emsp;&emsp;&emsp;&emsp; 使⽤这项重构⼿法之前，请先确定⾃⼰已经尽可能分解了原先函数。
替換⼀个巨⼤⽽复杂的算法是⾮常困难的，只有先将它分解为较简单的⼩型函数，然后你才能很有把握地进⾏算法替换⼯作。

## 做法
-[ ] 准备好另⼀个（替换⽤）算法，让它通过编译。 
-[ ] 针对现有测试，执⾏上述的新算法。如果结果与原本结果相同，重构结束。 
-[ ] 如果测试结果不同于原先，在测试和调试过程中，以旧算法为⽐较参照标准。 
   > 对于每个测试⽤例，分别以新旧两种算法执⾏，并观察两者结果是否相同. 
     这可以帮助你看到哪⼀个测试⽤倒出现麻烦，以及出现了怎样的麻烦.

