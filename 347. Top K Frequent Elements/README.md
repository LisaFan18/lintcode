## 前记
因为期末和holiday有2个月没刷题了,手又生了。

## Summary
1. hashMap.getOrDefault(key, 0); // default value is 0 if key is not found in the map
2. 用lamda function 自定义PriorityQueue的comparator
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((num1,num2)-> map.get(num1) - map.get(num2))
```
2.1 . the Syntax of lamda expression in java
(parameters) -> expression
refer to the detail in chapter 6.3.2 on CoreJava Volume1 

2.2. [Comparator VS. Comparable](https://stackoverflow.com/questions/2266827/when-to-use-comparable-and-comparator)
Comparator interface compare(Object o1, Object o2) method need to be implemented that takes two Object argument, 
it should be implemented in such a way that 
    returns negative int if the first argument is less than the second one and 
    returns zero if they are equal 
    returns positive int if the first argument is greater than the second one

## idea
跟709. kth largest element in a stream 类似。有两点改进：
1. 先统计每个element的frequency: iterate through the array，use a HashMap to store the element and its frequency. 
2. 指定PriorityQueue的comparator为 element's frequency. 

## Solution 1 ([HashTable + MinHeap](https://leetcode.com/problems/top-k-frequent-elements/solution/))
## Solution 2 ([HashTable + Bucket Sort](https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort))
