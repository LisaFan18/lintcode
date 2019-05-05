## Summary
0. Tenary operators ?: means myVariable = (testCondition) ? someValue : anotherValue; just same as 
```java
if (testCondition) {
    myVariable = someValue;
} else {
    myVariable = anotherValue;
}
// OK: return prev.next == null ? -1: prev.next.value; 
// totally Wrong: prev.next == null ? return : prev.next = prev.next.next;
```
[Solution](https://github.com/LisaFan18/lintcode/blob/master/706.%20Design%20HashMap/solution.java)
