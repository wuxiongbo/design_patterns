# Replace Temp with Query 以查询取代临时变量

## 动机
临时变量的问题在于：它们是暂时的，⽽且只能在所属函数内使⽤。

由于，临时变量 只在 所属函数内 可⻅，
所以，它们会驱使你写出更⻓的函数，因为，只有这样，你才能访问到 需要的临时变量。
如果，把 临时变量 替换为⼀个 查询，那么，同⼀个类中的 所有函数 都将可以获得这份信息。
这将带给你极⼤帮助，使你能够为这个类编写更清晰的代码。

Replace Temp with Query（120）往往是你运⽤ Extract Method（110）之前必不可少的⼀个步骤。
‘局部变量’ 会使代码 难以被提炼，所以，你应该尽可能把它们替换为 ‘查询式’。 

这个重构⼿法较为简单的情况是：临时变量 只被赋值⼀次，或者 赋值给 临时变量 的表达式 不受其他条件影响。 
其他情况⽐较棘⼿，但也有可能发⽣。
你可能需要先运⽤ Split Temporary Variable（128）或 Separate Query from Modifier（279） 
使情况变得简单⼀些，然后再替换临时变量。
如果你想替换的临时变量是⽤来收集结果的（例如，循环中的累加值），
就需要将某些程序逻辑（例如循环）复制到查询函数去。

## 做法

⾸先是简单情况： 

-[ ]  找出只被赋值⼀次的临时变量。
如果某个临时变量被賦值超过⼀次，考虑使⽤ Split Temporary Variable（128）将它分割成多个变量。 
-[ ]  将该临时变量声明为final。 
-[ ]  编译。
   >• 这可确保该临时变量的确只被赋值⼀次.
-[ ]  将“对该临时变量赋值”之语句的等号右侧部分提炼到⼀个独⽴函数中。
   >• ⾸先将函数声明为`private`。⽇后，你可能会发现有更多类需要使⽤它，那时放松对它的保护也很容易.  
    • 确保提炼出来的函数⽆任何副作⽤，也就是说该函数并不修改任何对象内容.
      如果它有副作⽤，就对它进⾏ Separate Query from Modify （279）
-[ ]  编译，测试。
-[ ]  在该临时变量身上实施 Inline Temp （119）。

我们常常使⽤ **临时变量** 保存循环中的 累加信息。
在这种情况下，整个循环都可以被提炼为⼀个独⽴函数，这也使原本的函数可以少掉⼏⾏扰⼈的循环逻辑。
有时候，你可能会在⼀个循环中累加好⼏个值，就像本书第26⻚的例⼦那样。
这种情况下，你应该针对每个累加值重复⼀遍循环，这样就可以将所有临时变量都替换为查询。 
当然，循环应该很简单，复制这些代码时才不会带来危险。

运⽤此⼿法，你可能会担⼼性能问题。和其他性能问题⼀样，我们现在不管它，因为它⼗有⼋九根本不会造成任何影响。
若是性能真的出了问题，你也可以在优化时期解决它。
代码组织良好，你往往能够发现更有效的优化⽅案：如果没有进⾏重构，好的优化⽅案就可能与你失之交臂。
如果性能实在太糟糕，要把临时变量放回去也是很容易的。
