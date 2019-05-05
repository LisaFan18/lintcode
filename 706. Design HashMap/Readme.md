## Summary
1. Tenary operators ?: means myVariable = (testCondition) ? someValue : anotherValue; just same as 
```java
if (testCondition) {
    myVariable = someValue;
} else {
    myVariable = anotherValue;
}
// OK: return prev.next == null ? -1: prev.next.value; 
// totally Wrong: prev.next == null ? return : prev.next = prev.next.next;
```
2. Hash function: 本题可以直接利用Object类naive的 hashCode(). 它的唯一性决定了他可以用来生成HashMap的key，同时也能判断对象是否为同一个对象。不过Integer.hashCode(key) 直接返回的就是input key的值
3. 程序模块化：get(), put(),remove()都用到了find方法。由于remove时需要prev.next = prev.next.next. 所有find方法return prev

[Solution](https://github.com/LisaFan18/lintcode/blob/master/706.%20Design%20HashMap/solution.java)
