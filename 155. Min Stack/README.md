## Summary
1. stack: 处理的数据需要保持last-in first out的顺序。ctrl+z就是基于stack实现的。现实生活stack场景有挤地铁，坐电梯
2. stack 属于linear 表，本质上跟array和linkedlist没区别。可以理解为阉割后的数组:  
数组支持任意位置的add,delete,update,get    
但stack只能在一端（top位置）add,delete,update,get
3. solution1的第二种解决方案，minStack需要保存多个相同的min，这是因为minStack里的element也是打擂台产生的，但如果是平局，都要保存，因为无法记录平局次数。
当然你也可以再声明一个变量记录平局次数，但平局有多种情况，比如平局时x=3或x=5。这就搞复杂了既要记录平局的次数，又要记录平局x值。还不如，直接把每次平局的值保存下来即可。
## Solution 2 （to do）
用1个stack： diffStack 和一个全局 int min.  
* push时, push(x-min), if x<min 更新min, min=x
* pop时, if top<0, 更新/increase min, min = min - top
* top时, 利用different和min还原data，if top>0 return top + min; if top < 0 return min
* getMin() 直接返回min即可
refer to [here](https://leetcode.com/problems/min-stack/discuss/49031/Share-my-Java-solution-with-ONLY-ONE-stack)

## Solution 1
### Idea
0. 第一反应是用varaible int min 每次push时更新min；但pop操作时更新min in O(n) time. 怎么办？walk through it with a short example 会发现
min值其实也遵守stack LIFO的顺序。最简单的方式 用两个stack：一个stack保存数据，一个stack保存min，只有当min值改变(x <= min)时push到minStack
1. 用两个stack: dataStack, minStack:   
* 长度一致。minStack里保存到当前x为止的最小值。与dataStack同步push，pop
* 长度不一致。注意如果当前push的值和min相等，minStack.push(x). 换句话说，**如果dataStack里有多个min， minStack里要保存多份**。
否则，例如 push(0), push(1), push(0), getMin(), pop(), getMin(). 如果使用<会在pop()后，minStack就会为empty    
  
```java
class MinStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    
    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        dataStack.push(x);
        // update the min, 如果有多个min，都要保存
        if(x <= getMin()){
        //if(x < getMin()){ // WRONG
           minStack.push(x); 
        }
        
    }
    
    public void pop() {
        // corner case, just return
        if(dataStack.isEmpty()){
            return;
        } 
        // update min 
        int val = dataStack.pop();
        if(val == getMin()){
            minStack.pop();
        }
    }
    
    public int top() {
       if(dataStack.isEmpty()){
            return Integer.MAX_VALUE; // Error value instead of throwing exception
        } 
        
       return dataStack.peek(); // API is peek() not top()
    }
    
    public int getMin() {
        if(minStack.isEmpty()){
            return Integer.MAX_VALUE;
        }
        
        return minStack.peek(); 
    }
}
```

