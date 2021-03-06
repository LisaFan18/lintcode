# Summary
1. hashtable: k-v pairs; 3 components: key, hashfunction, buckets(Eg: array of linkedlist) 
2. hash function: used to convert the key into an integer within \[0, HASH_SIZE). 
   index = hashFunction(key)%hash_size. 同一个key得到相同的index；但不保证不同的key得到相同的index 即collision
3. [Collision Resolution](http://www.cs.cmu.edu/~ab/15-121N11/lectures/lecture16.pdf): 
* Chaining [Visualization](https://www.cs.usfca.edu/~galles/visualization/OpenHash.html) (jdk 采用这种方式)
* Open Addressing Techniques [Visualization](https://www.cs.usfca.edu/~galles/visualization/ClosedHash.html) 
  * Linear Probing
  * Quadratic Probing
4. [rehashing](https://github.com/LisaFan18/lintcode/tree/master/129.%20Rehashing)

## Implementation 
1. 基于Array of linkedList来实现，也即是说Array里的元素是ListNode, 由于hashmap保存的是<k,v>pair,所以ListNode likes this
```java
  class ListNode {
        int key;
        int value;
        ListNode next;
        ListNode(int k, int v){
            this.key = k;
            this.value = v;
            this.next = null;
        }
    }
```
2. 一个好的hash function的标准：
* minize collision. a good hash function can avoid collision as less as possible. 
* easy to compute, 常见算法有hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 
* distributed key values **evenly** in the hash table
```java
  public int hashCode(char[] key, int HASH_SIZE) {
       long ans=0;
       for(char c: key){
        //   ans = ans*33 +  (int)c;
           ans = (ans*33 +  (int)c)%HASH_SIZE; // use the property of % operation to avoid overflow
       }
       
       return (int)ans%HASH_SIZE;
    }
```
3. rehashing 
* 什么时候扩容？Java 默认采用75% (load factor)。trade-off: load factor越小，collision的可能性越小，get/put效率越高，但存储空间越大。
* 怎么扩容？ 思想类似于Java里 dynamic ArrayList的思想 double its original size。不过ArrayList只是简单的copy: 而rehashing需要对所有元素都重新计算index。因为index依赖于bucket 的size，如果size is doubled，所有的index都需要重新计算。因此 rehashing 是个很费时的操作。

## HashMap, LinkedHashMap, TreeMap, HashTable
1. [How does Java HashMap handle collisions](https://javarevisited.blogspot.com/2016/01/how-does-java-hashmap-or-linkedhahsmap-handles.html)
* Prior to Java8, handle collison by Chaining: 
  * If a key end up in same bucket location where an entry is already stored then this entry is just added at the **head** of the linked list there.
  * In worst case, frequent HashMap collision happens, it degrades the performance of get() to O(n) from O(1)
* Java 8 uses a balanced tree instead of linked list for storing collided entries. 
  * In worst case, the performance of get() boost to O(logn) from O(n)
  * TREEIFY_THRESHOLD is the threshold of switching to the balanced tree. Currently, it's value is 8, which means if there are more than 8 elements in the same bucket than HashMap will use a tree instead of linked list to hold them in the same bucket. 
2. [Difference between HashMap, LinkedHashMap, TreeMap and HashTable](https://javarevisited.blogspot.com/2015/08/difference-between-HashMap-vs-TreeMap-vs-LinkedHashMap-Java.html)
* HashMap 
   * based on buckets, allowed null key, efficiency O(1)
   * doesn't provide any ordering guarantee for entries, 
* LinkedHashMap 
  * based double linkedlist of buckets, allowed null key, efficiency O(1)
  * sorted according to the insertion order,  
* TreeMap 
  * based on red-black tree, not allowed null key,efficiency O(logn)
  * sorted according to natural order of keys; 
* HashTable is obsolete, use ConcurrentHashMap


## Hash Function
1. Hash function
 * 固定无规律的"整数"：固定表示同一个key对应到相同的位置；无规律减少collision; 整数%hash_size对应到index。
 * jdk里hashMap的hashCode方法对Object的hashCode值进行了优化，使之更加的散列到不同的桶，因为只有每个桶的分布比较均匀的时候，查询效率才更快，如果都堆到一个桶的话，效率就和list没有区别了。
 *  java 8 [hashcode](http://www.majiang.life/blog/deep-dive-on-java-hashcode/) 算法采用的是： int类型的该对象的内存地址。 
 
2.  [几种常见的hash算法和原理](https://www.cnblogs.com/zhoug2020/p/6984177.html) 经常听到md5这些hash算法是用于加密的，不是这里的概念。

## Java hashCode()
1. [Java HashCode的作用原理和实例解析](https://blog.csdn.net/SEU_Calvin/article/details/52094115)
* HashCode的特性: 
  * 如果两个对象相同， equals方法一定返回true，并且这两个对象的HashCode一定相同；
  * 两个对象的HashCode相同，并不一定表示两个对象就相同，即equals()不一定为true，只能说明这两个对象在一个散列存储结构中。
  * 如果对象的equals方法被重写，那么对象的HashCode要override [why?](https://www.jianshu.com/p/da7491e5be53)
* HasCode的应用之Set.contains()
  * Set里的元素无序不重复。如何判断重复呢？如果每次都调用equals方法，如果set里元素很多时效率就很低了。怎么办？先调hashcode方法，
    * 哈希算法也称为散列算法，是将数据依特定算法直接指定到一个地址上。这样一来，当集合要添加新的元素时，先调用这个元素的HashCode方法，就一下子能定位到它应该放置的物理位置上。
    * 如果这个位置上没有元素，它就可以直接存储在这个位置上，不用再进行任何比较了；
    * 如果这个位置上已经有元素了，就调用它的equals方法与新元素进行比较，相同的话就不存了；不相同的话，也就是发生了Hash key相同导致冲突的情况，那么就在这个Hash key的地方产生一个链表，将所有产生相同HashCode的对象放到这个单链表上去，串在一起。
    * 这样一来实际调用equals方法的次数就大大降低了，几乎只需要一两次。

# Problems
| Number| Title         | Solution      | Note           | Difficulty    | Tag          |
| ------| ------------- | ------------- | -------------  | ------------- |------------- |
| 706 | [Design HashMap](https://leetcode.com/problems/design-hashmap/)| Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/706.%20Design%20HashMap) |  Easy  | HashMap, LinkedList |
| 128 | [Hash Function](https://www.lintcode.com/problem/hash-function/description)| Java | Note |  Easy  | Hash Function |
| 129 | [ReHashing](https://www.lintcode.com/problem/rehashing/description)| Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/129.%20Rehashing) |  Medium  | ReHash, Doubling |

