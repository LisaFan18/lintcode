# Summary
1. Hash function
 * 本题可以直接利用Object类naive的 hashCode(). 它的唯一性决定了他可以用来生成HashMap的key，同时也能判断对象是否为同一个对象。不过Integer.hashCode(key)
 直接返回的就是input key的值
 
 * 但jdk里hashMap的hashCode方法对Object的hashCode值进行了优化，使之更加的散列到不同的桶，
 因为只有每个桶的分布比较均匀的时候，查询效率才更快，如果都堆到一个桶的话，效率就和list没有区别了。

2. 基于Array of linkedList来实现，也即是说Array里的元素是ListNode, 由于hashmap保存的是<k,v>pair,所以ListNode likes this
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

## Hash Function
1. 一个好的hash function的标准：
* minize collision, 
* easy to compute, 
* distributed key values evenly in the hash table
2.  [几种常见的hash算法和原理](https://www.cnblogs.com/zhoug2020/p/6984177.html)

3.  java 8 [hashcode](http://www.majiang.life/blog/deep-dive-on-java-hashcode/) 算法采用的是： int类型的该对象的内存地址。 
4. [Java HashCode的作用原理和实例解析](https://blog.csdn.net/SEU_Calvin/article/details/52094115)
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
