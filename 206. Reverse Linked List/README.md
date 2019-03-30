## Summary
1. first to understand whether it's a **singly or doublely** LL?   
2. LL has a Recursion structure . If you're having trouble in solving LL, try recursion method 
3. Techniques: "faster and slower Runner", dummy pointer
4. Remember to check the null pointers and update the head or tail pointer as necessary

## Iterative Method
### idea
1. 首先想到two pointers prev, cur, 利用cur.next = prev 实现 reverse。不过问题来了，链表从cur这里断开分成了two parts 无法实现后续iterate。
怎么办？ 再加一个pointer next。使用three pointers technique来解决。

### solution 1
```java
   public ListNode reverseList(ListNode head) {
        //corner case
        if(head == null || head.next == null){
            return head;
        }
                
        // more than 1 elements
        ListNode prev = head, cur = head.next, newHead = null; 
        while(cur != null){
            newHead = cur; 
            ListNode next = cur.next;
            cur.next = prev;
            if(prev == head){
                prev.next = null;
            }
            
            // update prev, cur
            prev = cur;
            cur = next;
        }
        
        return newHead;
    }
```
## Recursive Method
### idea
1. What's recursive problem? it can be build off of **sub-problems**.  
          * recursive solution are built off solutions to sub-problems.   
          * compute f(n) by simply change(add or remove) the solution for f(n-1).   
2. Bottom-up Recursion  
   key: how to build the solution for one case off the previous case
3. Top-down Recursion  (more complex)  
   key: be careful of overlap between the cases
4. Iterative vs. Recursion 
   * recursion is space inefficient. each recursion call adds a new layer to the stack. If the algorithm has O(n) recursive calls, then it uses O(n) memory
   * All recursive code can be implemented iteratively, although sometimes the code to do so is much more complex. 
   * Recursion's prons/cons: **easy to implement, space inefficient**
5. [Time and space complexity of Recursive Algorithm](https://stackoverflow.com/questions/43298938/space-complexity-of-recursive-function)
 * Time: f = f(n-1) + f(n-2) => T(n) = 2T(n-1) => O(2^n). To build a **recursion tree**, since each node has 2 branches and we have n total levels, our total number of nodes is 2^n making our time complexity O(2^n).
 * Space: To generalize, a recursive function's memory complexity is O(recursion depth). Memory complexity is determined by the number of return statements 
 because each function call will be stored on the program stack. 
 
### solution 2
```java
   public ListNode reverseList(ListNode head) {
       // base case
       if(head == null || head.next == null){
           return head;
       }
        
        ListNode p = reverseList(head.next); // p is the newHead. Note: do not change it, just return it.
         // 在归来中解决
        head.next.next = head; 
        head.next = null; // why? otherwise infinity loop
        
        return p;      
    }
```

### recursion summary
input: 1->2->3->4->5->null; 在递归调用时，revserseList(1)首先被push到stack底部，接着是revserseList(2)，revserseList(3)，
revserseList(4)，revserseList(5). 由于 5.next == null 从而return 回到 context为revserseList(4)的function里。  
1. revserseList(5)这一层的返回值就是newHead，所以千万不要修改它，直接return newHead。    
2. 问题来了，如何实现reverse呢？具体到这个example如何实现5->4?   
  答案很简答！既然不能动newHead，当前函数context的里head指向的是4. 由于还未对original list做任何操作，list为1->2->3->4->5->null.  
  利用4.next拿到5，再reverse即 4.next.next = 4. 得到1->2->3->4**<->**5->null  
  此时 4,5互为对方的next node 会造成infinity loop。加上4.next = null 解决。  
