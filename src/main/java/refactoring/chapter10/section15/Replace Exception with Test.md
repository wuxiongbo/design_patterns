# Replace Exception with Test（以测试取代异常）

⾯对⼀个调⽤者可以预先检查的条件，你抛出了⼀个异常。

修改调⽤者，使它在调⽤函数之前先做检查。

## 动机

异常的出现是程序语⾔的⼀⼤进步。
运⽤Replace Error Code with Exception（310），异常便可协助我们避免很多复杂的错误处理逻辑。

但是，就像许多好东⻄⼀ 样，异常也会被滥⽤，从⽽变得不再让⼈愉快
（就连味道极好的Aventinus啤酒，喝得太多也会让我厌烦［Jackson］）。
“异常”只应该被⽤于 异常的、罕⻅的⾏为，也就是那些产⽣意料之外的错误的⾏为，⽽不应该成为条件检查的替代品。
如果你可以合理期望调⽤者在调⽤函数之前先检查某个条件，那么，就应该提供⼀个测试，⽽调⽤者应该使⽤它。

## 做法

-[ ] 在函数调⽤点之前，放置⼀个测试语句，将函数内catch区段中的代码复制到测试句的适当if分⽀中。
-[ ] 在catch区段起始处加⼊⼀个断⾔，确保catch区段绝对不会被执⾏。
-[ ] 编译，测试。
-[ ] 移除所有catch区段，然后将try区段内的代码复制到try之外，然后移除 try区段。
-[ ] 编译，测试。

## 范例

下⾯的例⼦中，我以⼀个 ResourcePool对象 ，管理⼀些创建代价⾼昂⽽又可以重复使⽤的资源（例如数据库连接）。
这个对象带有两个 “池”（pool）：
    ⼀个⽤以保存可⽤资源，
    ⼀个⽤以保存已分配资源。
当⽤户请求⼀份资源时，ResourcePool对象 从 “可⽤资源池” 中取出⼀份资源交出，并将这份资源转移到 “已分配资源池”。 
当⽤户释放⼀份资源时，ResourcePool对象 就将该资源从 “已分配资源池” 放回 “可⽤资源池”。
如果 “可⽤资源池” 不能满⾜⽤户的请求，ResourcePool对象 就创建⼀份新资源。 

资源供应函数可能如下所示：
```Java
class ResourcePool {
    Resource getResource() {
        Resource result;
        try {
            result = (Resource) _available.pop();
            _allocated.push(result);
            return result;

        } catch (EmptyStackException e) {
            result = new Resource();
            _allocated.push(result);
            return result;
        }

    }
    // 可用资源池
    Stack _available;
    // 已分配资源池
    Stack _allocated;
}


```

在这⾥，“可⽤资源⽤尽” 并不是⼀种意料外的事件，因此，我不该使⽤异常表示这种情况。  
为了去掉这⾥的异常，我⾸先必须添加⼀个适当的提前测试，并在其中处理 “可⽤资源池为空” 的情况：
```java
class ResourcePool {
    Resource getResource() {
        Resource result;
        
        // 提前测试，处理 “可⽤资源池为空” 的情况
        if (_available.isEmpty()) {
            result = new Resource();
            _allocated.push(result);
            return result;
        } else {
            try {
                result = (Resource) _available.pop();
                _allocated.push(result);
                return result;
            } catch (EmptyStackException e) {
                result = new Resource();
                _allocated.push(result);
                return result;
            }
        }
    }
    // 可用资源池
    Stack _available;
    // 已分配资源池
    Stack _allocated;
}

```

现在，getResource() 应该绝对不会抛出异常了。我可以 添加断⾔ 保证这⼀点：

```Java
class ResourcePool {
    Resource getResource() {
        Resource result;

        // 提前测试，处理 “可⽤资源池为空” 的情况
        if (_available.isEmpty()) {
            result = new Resource();
            _allocated.push(result);
            return result;
        } else {
            try {
                result = (Resource) _available.pop();
                _allocated.push(result);
                return result;
            } catch (EmptyStackException e) {
                Asaert.shouldNeverReachHere("available was empty on pop");
                result = new Resource();
                _allocated.push(result);
                return result;
            }
        }
    }

    // 可用资源池
    Stack _available;
    // 已分配资源池
    Stack _allocated;
}

class Assert {
    public static void shouldNeverReachHere(String message) {
        throw new RuntimeException(message);
    }
}
```

编译并测试。
如果⼀切运转正常，就可以将try区段中的代码复制到try区段之外，然后，将 try区段 全部移除：
```Java
class ResourcePool {
    Resource getResource() {
        Resource result;

        // 提前测试，处理 “可⽤资源池为空” 的情况
        if (_available.isEmpty()) {
            result = new Resource();
            _allocated.push(result);
            return result;
        } else {
            result = (Resource) _available.pop();
            _allocated.push(result);
            return result;
        }
    }

    // 可用资源池
    Stack _available;
    // 已分配资源池
    Stack _allocated;
}


```

在这之后我常常发现，可以对 条件代码 加以整理。
本例之中我可以使⽤ Consolidate Duplicate Conditional Fragments（243）合并重复的条件⽚段 ：
```Java
class ResourcePool {
    Resource getResource() {
        Resource result;

        // 合并重复的条件⽚段
        if (_available.isEmpty()) {
            result = new Resource();
        } else {
            result = (Resource) _available.pop();
        }
        
        _allocated.push(result);
        
        return result;
    }

    // 可用资源池
    Stack _available;
    // 已分配资源池
    Stack _allocated;
}
```