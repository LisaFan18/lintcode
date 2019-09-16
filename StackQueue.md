 # Queue
 1. It's a linear data structure. The principle of processing order is FIFO.  
 2. The key points of BFS is to use Queue. 
 ## implement
 1. inefficient way: a dynamic array, a head pointer; drawback: with the movement of head pointer, more and more space is wasted. 
 2. efficient way_circular queue: a fixed-size array; two pointers: head, tail; reuse the wasted space
 
 ## usage
 1. (Best): Deque<Integer> dq = new ArrayDeque<>(); API: q.offer(), q.poll(), q.peek(), q.size(). 
 2. (Not recommended): Queue<Integer> q = new LinkedList<>(); 即使提前知道queue的size，也不能这样initialize Queue<Integer> q = new LinkedList(size) 因为LinkedList的构造函数只有下面这两个。
 ```java
 LinkedList(): Constructs an empty list.
LinkedList(Collection<? extends E> c): Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator.
```
仔细想想，linkedlist的insert操作是通过new一个LinkedlistNode实现。 不是array. java built-in dynamic ArrayList的构造函数如下：
```java
ArrayList(): Constructs an empty list with an initial capacity of ten.
ArrayList(Collection<? extends E> c): Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator.
ArrayList(int initialCapacity): Constructs an empty list with the specified initial capacity.
```
## BFS

 
# Stack 
1. stack: 处理的数据需要保持last-in first out的顺序。ctrl+z就是基于stack实现的。现实生活stack场景有挤地铁，坐电梯
2. stack 属于linear 表，本质上跟array和linkedlist没区别。可以理解为阉割后的数组:  
数组支持任意位置的add,delete,update,get    
但stack只能在一端（top位置）add,delete,update,get
## implement
1. easy/best: a dynamic array is sufficent to implement a stack
2. based on linked list: insert the new item at the head/top of the linkedlist. 

## usage
1. ~(deprecated) Stack<Integer> s = new Stack<>(); API: s.pop(), s.push(), s.peek(), s.size()~
2. Deque<Integer> stack = new ArrayDeque<>(); API: s.pop(), s.push(), s.peek(), s.size().

## DFS Pseudo code (recursion)
```java
void dfs(Node root){
  // stop case
  if(root == null){
     return;
  }
  visited(root);
  for(Node n in root.adjacent){
    if(n.visited == false){
       dfs(n);
    }
  }
}
```
 # ArrayDeque
1. An ArrayDeque (also known as an “Array Double Ended Queue”, pronounced as “ArrayDeck”) is a special kind of a growable array that allows us to add or remove an element from both sides
2. [How's ArrayDeque implemented?](https://www.baeldung.com/java-array-deque) 
 * ArrayDeque is backed by an array which **doubles its size** when it gets filled.
 * Initially, the array is initialized with a size of 16. 
 * It's implemented as a **double-ended queue** where it maintains **two pointers namely a head and a tail**.
3. [ArrayDeque is the fastest Queue Implementation](https://stackoverflow.com/questions/6129805/what-is-the-fastest-java-collection-with-the-basic-functionality-of-a-queue)
 * ArrayDeque doesn't have the overhead of node allocations that LinkedList does 
 * ArrayDeque doesn't have the overhead of shifting the array contents left on remove that ArrayList has
4. [Why I should use Deque over Stack](https://stackoverflow.com/questions/12524826/why-should-i-use-deque-over-stack)
 * Javadoc says: Deques can also be used as LIFO (Last-In-First-Out) stacks. This interface should be used in preference to the legacy Stack class.
 * Deque.iterator() has a reverse iteartion order; Deque.descendingIterator(). eg. deque.push(1);deque.push(2);deque.push(3);   System.out.println(new ArrayList<>(deque)); // prints 3, 2, 1 
 
 # Problems 
 | Number| Title         | Solution      | Note           | Difficulty    | Tag          |
| ------| ------------- | ------------- | -------------  | ------------- |------------- |
| 346| [Moving Average from Data Stream](https://leetcode.com/problems/moving-average-from-data-stream/)  | Java  | [Note](https://github.com/LisaFan18/lintcode/tree/master/346.%20Moving%20Average%20from%20Data%20Stream)   | EASY  | Queue,sliding window |
| 622| [Circular Queue](https://leetcode.com/problems/design-circular-queue/)  | Java  | [Note](https://github.com/LisaFan18/lintcode/tree/master/622.%20Design%20Circular%20Queue)   | Medium  | Queue |
| 155| [Min Stack](https://leetcode.com/problems/min-stack/)  | Java  | [Note](https://github.com/LisaFan18/lintcode/tree/master/155.%20Min%20Stack)   | Medium  | Two stacks or Stack with the difference |
