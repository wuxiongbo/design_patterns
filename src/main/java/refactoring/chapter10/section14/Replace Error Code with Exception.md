# Replace Error Code with Exception（以异常取代错误码）

某个函数返回⼀个特定的代码，⽤以表示某种错误情况。 

改⽤异常。
```Java
class Demo {
    /**
     * -1 错误
     *  0 成功
     * @param amount
     * @return
     */
    int withdraw(int amount) {
        if (amount > _balance) {
            return -1;
        } else {
            _balance -= amount;
            return 0;
        }
    }
}
```
```Java
class Demo {
    void withdraw(int amount) throws BalanceException {
        if (amount > _balance) {
            throw new BalanceException();
        }
        _balance -= amount;
    }
}
```

## 动机

和⽣活⼀样，计算机偶尔也会出错。⼀旦事情出错，你就需要有些对策。
最简单的情况下，你可以停⽌程序运⾏，返回⼀个错误码。
这就好像因为错过⼀班⻜机⽽⾃杀⼀样（如果真那么做，哪怕我是只猫，我的九条命也早赔光了）。
尽管，我的油腔滑调 企图带来⼀点幽默，但这种“软件⾃杀”选择的确是有好处的。
如果程序崩溃代价很⼩，⽤户⼜⾜够宽容，那么就放⼼终⽌程序的运⾏好了。
但如果你的程序⽐较重要，就需要以更认真的⽅式来处理。  

问题在于：程序中发现错误的地⽅，并不⼀定知道如何处理错误。
当⼀段 ⼦程序 发现错误时，它需要让它的调⽤者知道这个错误，⽽调⽤者也可能将这个错误继续沿着调⽤链传递上去。
许多程序都使⽤ 特殊输出来表示错误，
Unix系统 和 C-based系统 的传统⽅式 就是 以 返回值 表示 ⼦程序的 成功 或 失败。

Java有⼀种更好的错误处理⽅式：异常。
这种⽅式之所以更好，因为它清楚地将 “普通程序” 和 “错误处理” 分开了，
这使得程序更容易理解 —— 我希望你如今已经坚信：代码的可理解性应该是我们虔诚追求的⽬标。 

## 做法

⼝ 决定应该抛出受控（checked）异常还是⾮受控（unchecked）异常。  
• 如果调⽤者有責任在调⽤前检查必要状态，就抛出⾮受控异常.  
• 如果想拋出受控异常，你可以新建⼀个异常类，也可以使⽤现有的异常类.  

⼝ 找到该函数的所有调⽤者，对它们进⾏相应调整，让它们使⽤异常。  
• 如果函数拋出⾮受控异常，那么就调整调⽤者，使其在调⽤函数前做适当 检查。每次修改后，编译并测试。  
• 如果函数抛出受控异常，那么就调整调⽤者，使其在Lry区段中调⽤该函数。  

⼝ 修改该函数的签名，令它反映出新⽤法。  


如果，函数有许多调⽤者，上述修改过程可能跨度太⼤。  
你可以将它分成下列数个步骤:

⼝ 决定应该抛出受控异常还是⾮受控异常。   
⼝ 新建⼀个函数，使⽤异常来表示错误状况，将旧函数的代码复制到新函数中，并做适当调整。
⼝ 修改旧函数的函数本体，让它调⽤上述新建函数。  
⼝ 编译，测试。  
⼝ 逐⼀修改旧函数的调⽤者，令其调⽤新函数。每次修改后，编译并测试。  
⼝ 移除旧函数。


## 范例

现实⽣活中，你可以透⽀你的账户余额，计算机教科书却总是假设你不能这样做，这不是很奇怪吗？
不过下⾯的例⼦仍然假设你不能这样做：
```Java
class Account {
    int withdraw(int amount) {
        if (amount > _balance) {
            return -1;
        } else {
            _balance -= amount;
            return 0;
        }
    }
    
    private int _balance;
}

```

为了让这段代码使⽤异常，我⾸先需要决定使⽤受控异常还是⾮受控异常。
决策关键在于：
调⽤者 是否有责任 在取款之前 检查存款余额，还是应该由 withdraw()函数 负责检查。
如果“检查余额”是调⽤者的责任，那么 “取款⾦额⼤于存款余额” 就是⼀个编程错误。
由于，这是⼀个编程错误，所以，我应该使⽤⾮受控异常。
另⼀⽅ ⾯，如果“检查余额”是 withdraw()函数 的责任，我就必须在函数接⼝中声明它可能抛出这个异常，
那么，也就提醒了调⽤者注意这个异常，并采取相应措施。

## 范例：⾮受控异常

⾸先，考虑⾮受控异常。
使⽤这个东⻄就表示应该由调⽤者负责检查。
⾸先，我需要检查调⽤端的代码，它不应该使⽤ withdraw()函数的返回值，因为该返回值只⽤来指出程序员的错误。
如果我看到下⾯这样的代码：
```Java
public static void main(String[] args) {
    if (account.withdraw(amount) == -1) {
        handleOverdrawn();
    } else {
        doTheUsualThing();
    }
}
```

我应该将它替换为这样的代码：
```Java
void withdraw(int amount) {
    if (account.canWithdraw(amount)) {
        handleOverdrawn();
    } else {
        doTheUsualThing();
    }
}
```
每次修改后，编译并测试。  
现在，我需要移除错误码，并在程序出错时抛出异常。
由于这种⾏为是 异常的、罕⻅的，所以，我应该⽤⼀个卫语句检查这种情况：
```Java
void withdraw (int amount){
    if (amount > _balance) {
        throw new IlegalArgumentException("Amount too large");
    }
    _balance -= amount;
}
```

由于这是程序员所犯的错误，所以，我应该使⽤ 断⾔，更清楚地指出这⼀点：

```Java
class Account {
    void withdraw(int amount) {
        Assert.isTrue("sufficient funds", amount <= _balance);
        _balance -= amount;
    }
}

class Assert {
    static void isTrue(String comment, boolean test) {
        if (!test) {
            throw new RuntimeException("Assertion failed: " + comment);
        }
    }
}
```


## 范例：受控异常

受控异常的处理⽅式略有不同。⾸先我要建⽴（或使⽤）⼀个合适的异常： 
```Java
class BalanceException extends Exception {}
```

然后，调整调⽤端如下：
```Java
void client(int amount){
    try {
        account.withdraw(amount);
        doTheUsualThing();
    } catch (BalanceException e) {
        handleOverdrawn();
    }
}
```


接下来我要修改 withdraw()函数，让它以异常表示错误状况：

```Java
class Account {
    int _balance;
    int withdraw(int amount) throws BalanceException {
        if (amount > _balance) {
            throw new BalanceException();
        } 
        _balance -= amount;         
    }
}

```

这个过程的麻烦在于：我必须⼀次性修改所有调⽤者和被它们调⽤的函数，否则编译器会报错。
如果调⽤者很多，这个步骤就实在太⼤了，其中没有编译和测试的保障。

这种情况下，我可以借助⼀个临时中间函数。
我仍然从先前相同的情况出发：
```Java
void client(int amount) {
    if (account.withdraw(amount) == -1) {
        handleOverdrawn();
    } else {
        doTheUsualThing();
    }
}
```
```Java
class Account {
    int _balance;
    int withdraw(int amount) {
        if (amount > _balance) {
            return -1;
        } else {
            _balance -= amount;
            return 0;
        }
    }
}
```

⾸先，创建⼀个newWithdraw()函数，让它抛出异常：
```Java
class Account {
    int _balance;
    
    int withdraw(int amount) {
        if (amount > _balance) {
            return -1;
        } else {
            _balance -= amount;
            return 0;
        }
    }
    
    int newWithdraw(int amount) throws BalanceException {
        if (amount > _balance) {
            throw new BalanceException();
        }
        _balance -= amount;
    }
}
```

然后，调整现有的 withdraw()函数，让它调⽤ newWithdraw()：
```Java
class Account {
    int _balance;
    int withdraw(int amount) {
        try {
            newWithdraw(amount);
            return 0;
        } catch (BalanceException e) {
            return -1;
        }
    }

    int newWithdraw(int amount) throws BalanceException {
        if (amount > _balance) {
            throw new BalanceException();
        }
        _balance -= amount;
    }
}
```
完成以后，编译并测试。
现在，我可以逐⼀将调⽤旧函数的地⽅改为调⽤新函数：

```Java
void client(int amount) {
    try {
        account.newWithdraw(amount);
        doTheUsualThing();
    }catch (BalanceException e){
        handleOverdrawn();
    }
}
```

由⼦新旧两个函数 都存在，所以，每次修改后我都可以编译、测试。
所有调⽤者都修改完毕后，旧函数便可移除，
并，使⽤ Rename Method（273）修改新函数名称，使它与旧函数相同。
