## 前记
因为期末和holiday有2个月没刷题了,手又生了。

## Summary
1. hashMap.getOrDefault(key, 0); // default value is 0 if key is not found in the map
2. [what's java 匿名类](https://www.jianshu.com/p/a59a31eb3a41) 匿名类相当于在定义类的同时在新建这个类的实例  
[Anonymous class syntax](https://www.cnblogs.com/yjmyzz/p/3448330.html)   

```java
Comparator<User> c = new Comparator<User>(){
    public int compare(User u1, User u2){
        return u1.id.compareTo(u2.id);
    }
   };

new Comparator<Map.Entry<Integer, Integer>(){
    public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2){
        return e1.getValue() - e2.getValue();
    }
};
```
2. Map<Integer, Integer> hashMap = new HashMap **<>** (); Recommended format  
   Map<Integer, Integer> hashMap = new HashMap();  
   throws Warning: missing type arguments for generic class HashMap<K,V>  
3. [java.util.Arrays](https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html) contains various methods for manipulating arrays (such as sorting and searching).  
例如int[] nums = {1,3,2}; Arrays.sort(nums, 0, 3) Note: toIndex is not included

4. [java.util.Collections](https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html) consists exclusively of static methods that operate on or return collections.
5. [java.util interface Collection<E>](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) known implemented classes: ArrayList, LinkedList
6. [java.util interface List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) 
    known implemented classes: ArrayList, LinkedList;   
    known implemented methods: subList(int fromIndex, int toIndex)

## lamda function
1. 用lamda function 自定义PriorityQueue的comparator
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((num1,num2)-> map.get(num1) - map.get(num2))
```
2. [PriorityQueue constructor syntax](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html)  
**PriorityQueue(Comparator<? super E> comparator)** Creates a PriorityQueue with the default initial capacity and whose elements are ordered according to the specified comparator.    

3. Syntax of lamda expression:  
 **(parameters) -> expression**  refer to the detail in chapter 6.3.2 on CoreJava Volume1 

## Comparator
1. [java.util.Comparator vs. java.lang.Comparable](https://stackoverflow.com/questions/2266827/when-to-use-comparable-and-comparator)  

2. [java.util.Comparator](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)  
**int compare(Object o1, Object o2)**  Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second. 
    
3. Comparator example (old way)
```java
   Collections.sort(users, new Comparator<User>() {
      public int compare(User u1, User u2) {
        return u1.id.compareTo(u2.id);
      }
    });


    Collections.sort(users, new Comparator<User>() {
      public int compare(User u1, User u2) {
        return u1.firstName.compareTo(u2.firstName);
      }
    });

```
4. Comparator example (lamda)
```java 
    Collections.sort(users, (User u1, User u2) -> u1.id.compareTo(u2.id));
    Collections.sort(users, (User u1, User u2) -> u1.firstName.compareTo(u2.firstName));
```

## idea
跟709. kth largest element in a stream 类似。有两点改进：
1. 先统计每个element的frequency: iterate through the array，use a HashMap to store the element and its frequency. 
2. 指定PriorityQueue的comparator为 element's frequency. 

## Solution 1
[Hashtable + Collections.sort](https://github.com/LisaFan18/lintcode/blob/master/347.%20Top%20K%20Frequent%20Elements/solution1.java)

## Solution 2
[HashTable + MinHeap](https://github.com/LisaFan18/lintcode/blob/master/347.%20Top%20K%20Frequent%20Elements/solution2.java)

## Solution 3 
[HashTable + Bucket Sort](https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort)
