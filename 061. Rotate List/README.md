## summary
1. corner case: null/1->null/1->2->null, k = 1
```java
if(head == null || head.next == null){
   return head;
}
```

2. dummy node 的使用

3. 唯一要注意的在于: 如果 k > linklist.size(n) 怎么办？

例如 1->2->3->null, k = 2000000, time limit exceeded。my solution 1 is O(K*n) 

Ex: {1,2,3} k=2 Move the list after the 1st node to the front

Ex: {1,2,3} k=5, In this case Move the list after (3-5%3=1)st node to the front.



用示例来表示到底什么叫 rotate the list to the right by k places.

k|	origin|	after
--- | --- | ---|
1|	0->1->2| 2->0->1
2|  0->1->2| 1->2->0
3|	0->1->2| 0->1->2
4|  0->1->2| 2->0->1|

可以观察的出来，当 k > n 的时候，k = k % n

solution for time limit exceeded 
   1. get the total length
   2. k = k % length, avoid cycle

参考：
1. https://leetcode.com/problems/rotate-list/discuss/22715/Share-my-java-solution-with-explanation 
2. https://github.com/pezy/LeetCode/tree/master/060.%20Rotate%20List 

## solution 1 (time limit exceeded)
```java
public ListNode rotateRight(ListNode head, int k) {
     // corner case
     if(head == null || head.next == null){
         return head;
     }
     for(int j=0; j<k; j++){
         ListNode ptl = head, ptr = head.next;
         // two pointers find the right place
         while(ptr.next != null){
             ptl = ptl.next;
             ptr = ptr.next;
         }

         // rotate 
         ptl.next.next = head;
         head = ptl.next; 
         ptl.next = null;
     }

     return head;
}
```
