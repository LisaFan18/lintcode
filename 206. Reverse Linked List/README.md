## Summary
1. first to understand whether it's a **singly or doublely** LL?   
2. LL has a Recursion structure . If you're having trouble in solving LL, try recursion method 
3. Techniques: "faster and slower Runner", dummy pointer
4. Remember to check the null pointers and update the head or tail pointer as necessary

## Iterative Method
### idea
1. 首先想到two pointers prev, cur, 利用cur.next = prev 实现 reverse。不过问题来了，链表从cur这里断开分成了two parts 无法实现后续iterate。
怎么办？ 再加一个pointer next。使用three pointers technique来解决。  
2. initial prev = null can combine the corner case and common cases; there is no need to declare newHead. 

### solution 1 (cleaner)
```java
 public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head; 
        while(curr != null){
            ListNode tempNext = cur.next;
            // reverse
            cur.next = prev;
            // move prev, curr to iterate the rest of LL
            prev = cur;
            cur = tempNext;
        }
        
        return prev;
    }
```
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

## Recursion Method 
 
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
