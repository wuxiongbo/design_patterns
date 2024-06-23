# Encapsulate Collection（封装集合）
有个函数返回⼀个集合。 


让这个函数返回该集合的⼀个只读副本，并在这个类中提供添加/移除集合 元素的函数。
```plantuml
class Person {
    -Set courses
    +Set getCourses(): Set
    +void setCourses(Set courses)
}
```
```plantuml
class Person {
    -Set courses
    +Set getCourses(): Set
    +void addCourse(Course course)
    +void removeCourse(Course course)
}
```

## 动机

我们常常会在⼀个类中，使⽤集合（collection，可能是 array、list、set 或 vector）来保存⼀组实例。
这样的类通常也会提供针对该集合的取值/设值函数。 
但是，集合的处理⽅式应该和其他种类的数据略有不同。
1）取值函数不该返回集合⾃身，因为这会让⽤户得以修改集合内容⽽集合拥有者却⼀⽆所悉。
这也会对⽤户暴露过多对象内部数据结构的信息。
2）如果⼀个取值函数确实需要返回多个值，它应该避免⽤户直接操作对象内所保存的集合，并隐藏对象内与⽤户⽆关的数据结构。 
⾄于如何做到这⼀点，视你使⽤的Java版本不同⽽有所不同。
3）另外，不应该为这整个集合提供⼀个设值函数，但应该提供⽤以为集合添加/移除元素的函数。
这样，集合拥有者（对象）就可以控制集合元素的添加和移除。

如果你做到以上⼏点，集合就被很好地封装起来了，这便可以降低集合拥有者和⽤户之间的耦合度。


## 做法

⼝ 加⼊为集合添加/移除元素的函数。 
⼝ 将保存集合的字段初始化为⼀个空集合。 
⼝ 编译。
⼝ 找出集合设值函数的所有调⽤者。
   你可以修改那个设值函数，让它使⽤上述新建⽴的“添加/移除元素”函数；
   也可以直接修改调⽤端，改让它们调⽤ 上述新建⽴的“添加/移除元素”函数。
> 两种情况下，需要⽤到集合设值函数：（1）集合⼒空时；（2）准备将原有集合替换为另⼀个集合时.  

> 你或许会想运⽤ Rename Method （273）为集合设值函数改名：从setXxx （） 改为initializeXxx（）或 replaceXxx （）

⼝ 编译，测试。 
⼝ 找出所有“通过取值函数获得集合并修改其内容”的函数。
逐⼀修改这些函 数，让它们改⽤添加/移除函数。
每次修改后，编译并测试。 

⼝ 修改完上述所有“通过取值函数获得集合并修改集合内容”的函数后，修改取值函数⾃身，使它返回该集合的⼀个只读副本。
> 在Java 2中，你可以使⽤collection.unmodifiableXxx（）得到该集合的只读副本。 

⼝ 编译，测试。

⼝ 找出取值函数的所有⽤户，从中找出应该存在于集合所属对象内的代码。
   运⽤ Extract Method （110）和 Move Method （142）将这些代码移到宿主对象去。

⼝ 编译，测试。

## 范例

假设有个⼈要去上课。我们⽤⼀个简单的course来表示“课程”：

```java
class Course {
    public Course(String name, boolean isAdvanced);
    public boolean isAdvanced();
}

```

我不关⼼课程其他细节。我感兴趣的是表示“⼈”的Person：
```java
class Person {
    private Set courses;
    public Set getCourses(){
        return courses;   
    }
    public void setCourses(Set courses){
        this.courses = courses;
    }
}

```


有了这个接⼝，我们就可以这样为某⼈添加课程：

```java
public static void main(String[] args) {
    Person kent = new Person();
    
    // 添加课程
    Set s = new HashSet();
    s.add(new Course("Smalltalk Programming", false));
    s.add(new Course("Appreciating Single Malts", true));
    
    kent.setCourses(s);
    
    Assert.equals(2, kent.getCourses().size());
    
    // 添加课程
    Course refact = new Course("Refactoring", true);
    kent.getCourses().add(refact);
    // 添加课程
    kent.getCourses().add(new Course("Brutal Sarcasm", false));
    
    Assert.equals(4, kent.getCourses().size());
    
    // 移除课程
    kent.getCourses().remove(refact);
    
    Assert.equals(3, kent.getCourses().size());
}
```

如果想了解⾼级课程，可以这么做：
```java
public static void main(String[] args) {
    Iterator iter = kent.getCourses().iterator();

    int count = 0;
    while (iter.hasNext()){
        Course each = (Course) iter.next();
        if (each.isAdvanced()) count++;
    }
}
```

我要做的第⼀件事就是为Person中的集合建⽴合适的修改函数（即 添加/移除函数），如下所示，然后编译：
```java
class Person {
    private Set courses;
    public Set getCourses(){
        return courses;   
    }
    public void setCourses(Set courses){
        this.courses = courses;
    }
    public void addCourse(Course course){
        courses.add(course);
    }
    public void removeCourse(Course course){
        courses.remove(course);
    }
}

```


如果像下⾯这样初始化 _courses字段，我的⼈⽣会轻松得多：
`private Set _courses = new HashSet()；`


接下来，我需要观察设值函数的调⽤者。
如果有许多地点⼤量运⽤了设值函数， 就需要修改设值函数，令它调⽤ 添加/移除函数。
这个过程的复杂度取决于 设值函数 的被使⽤⽅式。
设值函数的⽤法有两种，最简单的情况就是：它被⽤来初始化集合。 
换句话说，设值函数被调⽤之前，_courses是个空集合。
这种情况下只需修改 设值函数，令它调⽤添加函数就⾏了：

```java
class Person {
    public void setCourses(Set arg){
        Assert.isTrue(_courses.isEmpty());
        Iterator iter = arg.iterator();
        while (iter.hasNext()){
            addCourse((Course) iter.next());
        }
    }
}

```


修改完毕后，最好以Rename Method （273）更明确地展示这个函数的意图。
```java
class Person {
    public void initializeCourses(Set arg){
        Assert.isTrue(_courses.isEmpty());
        Iterator iter = arg.iterator();
        while (iter.hasNext()){
            addCourse((Course) iter.next());
        }
    }
}


```

更普通的情况下，我必须 ⾸先调⽤ 移除函数 将集合中的所有元素全部移除，
然后再调⽤ 添加函数 将元素——添加进去。
不过我发现这种情况很少出现（唔，愈是普通的情况，愈少出现）。

如果我知道初始化时，除了添加元素，不会再有其他⾏为，那么我可以不使⽤循环，直接调⽤addAll()函数：
```java
class Person {
    public void initializeCourses(Set arg){
        Assert.isTrue(_courses.isEmpty());
        _courses.addAll(arg);
    }
}
```

我不能直接把传⼊的set賦值给_courses字段，就算原本这个字段是空的也不⾏。
因为万⼀⽤户在把 set传递给 Person对象之后，⼜去修改set中的元素，就会破坏封装。
我必须像上⾯那样创建set的⼀个副本。

如果⽤户仅仅只是创建⼀个set，然后使⽤设值函数(initializeCourses)，我可以让它们直接使⽤ 添加/移除函数，并将 设值函数 完全移除。
于是，以下代码：
```java
public static void main(String[] args) {
    Person kent = new Person();
    Set s = new HashSet();
    s.add(new Course("Smalltalk Programming", false));
    s.add(new Course("Appreciating Single Malts", true));
    kent.initializeCourses(s);
}
```

就变成了：
```java
public static void main(String[] args) {
    Person kent = new Person();
    kent.addCourse(new Course("Smalltalk Programming", false));
    kent.addCourse(new Course("Appreciating Single Malts", true));
}
```

接下来，我开始观察取值函数的使⽤情况。⾸先处理 “通过取值函数修改集合元素” 的情况，例如：
`kent .getCourses （）.add（new Course （'Brutal Sarcasm'， false））；`

这种情况下我必须加以改变，使它调⽤新的修改函数：
`kent.addCourse（new Course （'Brutal Sarcasm'， false））；`

修改完所有此类情况之后，我可以让取值函数返回⼀个只读副本，⽤以确保没有任何⼀个⽤户能够通过取值函数修改集合：
```java
public Set getCourses(){
    return Collections.unmodifiableset(_courses);
}
```

这样，我就完成了对集合的封装。此后，不通过 Person提供的 添加/移除函数，谁也不能修改集合内的元素。

### 将⾏为移到这个类中

我拥有了合理的接⼝。现在开始观察取值函数的⽤户，从中找出应该属于Person 的代码。下⾯这样的代码就应该搬移到Person去：


```java
class Person {
    public int numberOfAdvancedCourses(){
        Iterator iter = getCourses().iterator();
        int count = 0;
        while (iter.hasNext()){
            Course each = (Course) iter.next();
            if (each.isAdvanced()) count++;
        }
        return count;
    }
}

```

因为以上只使⽤了属于Person的数据。⾸先我使⽤Extract Method （110）将这段代码提炼为⼀个独⽴函数：
然后，使⽤Move Method （142）将这个函数搬移到Person中：
下列代码是⼀个常⻅的例⼦：

`kent.getCourses().size()`

可以将其修改成更具可读性的样⼦，像这样：
`kent.numberOfCourses()`

`class Person..  
   public int numberOfCourses(){  
     return _courses.size();  
   }  
`

数年以前，我曾经担⼼将这样的⾏为搬移到Person中会导致Person变得臃肿。 
但是在实际⼯作经验中，我发现这通常并不成为问题。

### 范例：封装数组

数组经常被使⽤，特别是对于那些不熟悉集合的程序员⽽⾔。
我很少使⽤数组，因为我更喜欢功能更加丰富的集合类。
进⾏封装时，我常把数组换成其他集合。
这次我们的范例从⼀个字符串数组开始：
```java
class Person {
    String[] getSkills(){
        return _skills;
    }
    void setSkills(String[] arg){
        _skills = arg;
    }
    private String[] _skills;
}
```
同样地，⾸先要提供⼀个修改函数。由于⽤户有可能修改数组中某⼀特定位置上的值，所以我提供的getSkills()必须能对任何特定位置上的元素赋值：
```java
void setSkill(int index, String skill){
    _skills[index] = skill;
}
```

如果我需要对整个数组赋值，可以使⽤下列函数：
```java
void setSkills(String[] arg){
    _skills = new String[arg.length];
    for (int i = 0; i < arg.length; i++) {
        setSkill(i, arg[i]);
    }
}
```

如果需要处理从数组中移除元素，就会有些困难。
如果作为参数传⼊的 数组 和 原数组⻓度不同，情况也会⽐较复杂。
这也是我优先选择集合的原因之⼀。现在，我需要观察取值函数的调⽤者。我可以把下列代码：
`kent.getSkills（） ［1］ = "Refactoring"；`
改成：
`kent.setSkill（1， 'Refactoring"）；`

完成这⼀系列修改之后，我可以修改取值函数，令它返回⼀份数组副本：
```java
String[] getSkills(){
    String[] result = new String[_skills.length];
    System.arraycopy(_skills, 0, result, 0, _skills.length);
    return result;
}
```
现在，是把数组换成list的时候了：
```java
class Person {
    String[] getSkills() {
        return (String[]) _skills.toArray(new String[0]);
    }
    void setSkills(int index, String newSkill) {
        _skills.set(index, newSkill);
    }
    List _skills = new ArrayList();
}
```