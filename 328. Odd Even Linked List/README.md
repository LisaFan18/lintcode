昨天a家面试，遇到这个题目。自己用2 passes of linear scan the LL,第一遍挑出even nodes； 第二遍挑出odd nodes
但这样space complexity是 O(n)。面试官要求in place的算法。由于自己少声明了variable，加上coding前没跟面试官沟通好思路，没ask for hints。 导致越写越绕。

## Summary
1. LL的go through： 一定有一个head，cursor; LL的遍历利用cursor = cursor.next实现
2. 既然题目要求split成odd LL, even LL那么就有oddHead, oddCursor, evenHead, oddCursor. 
3. 如何判断oddHead = cursor? 还是oddCursor.next = cursor? 其实这是oddHead初始化的问题， if oddHead == null来区分。
4.  Note: evenPtr.next == null, otherwise a cycle will exist in the result ll.  
* input: 2->1->3->5->6->null;
* oddLL: 2->3->6->null; oddPtr is at 6
* evenLL: 1->3->5->6->null; evenPtr is at 5
* oddPtr.next = evenHead will get the ll: 2->3->6->1->3->5->6. (cycle as 6 appear two times)
* solution for avoiding cycle: evenPtr.next = null; the result ll will be 2->3->6->1->3->5->null

##Solution
```java
public ListNode oddEvenList(ListNode head) {
        // base case
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode oddHead = null;
        ListNode oddPtr = null;
        ListNode evenHead = null;
        ListNode evenPtr = null;
        ListNode cur = head;
        boolean isOdd = true;
       
        while(cur != null){
            if(isOdd){
               // initialize
               if(oddHead == null){
                   oddHead = cur;
                   oddPtr = oddHead;
               }else{
                   oddPtr.next = cur;
                   oddPtr = oddPtr.next;         
               }
            }else{
               if(evenHead == null){
                  evenHead = cur;
                  evenPtr = evenHead;
               }else{
                  evenPtr.next = cur;
                  evenPtr = evenPtr.next;
               }
            }
            
            cur = cur.next;
            isOdd = !isOdd;
        }
    
        // link the odd and even ll
        oddPtr.next = evenHead;
        // Note: need point to null otherwise cycle is generated.
        evenPtr.next = null; 
        return head;
    }
```
