## Summary
1. 既然有cycle，那么某个ListNode将会被visited two times。 如何知道某个ListNode's vistied times? 很简单。声明一个Set来记录被访问过的node  
2. challenge：O(1) space complexity. hint说用two pointers。 
* two pointers如何初始化（同向运动，还是相向运动）？   
  input是LinkedList, 我们只知道head，此处应该是同向运动。因为如果有cycle，那么就没有tail，不可能有相向运动。   
* 在什么条件下move pointers？  没思路。反过来想：  
  * 如果没有cycle，那么某个pointer会指向null；
  * 如果有cycle：two pointers会at some points相等！这好比two runners with different speed run in cycle tracks. boom！《crack》说过
  Two Runner (faster and slower） technique 是LinkedList这种数据结果常用技巧。 
  * thus，此处two pointer的移动并不像常规的two pointer题目，有明确的条件来操纵指针的移动。而是人为指定slow moves 1 step at a time；
  faster moves 2 steps at a time. 
 
 ## Solution 1 O(1) space
 ```java
 public boolean hasCycle(ListNode head) {
        // corner case
        if(head == null || head.next == null){
            return false;
        }
        
        ListNode slow = head;
        // ListNode fast = head; // WRONG
        ListNode fast = head.next; 
        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next; 
        }
        
        return true;     
    }
 ```
 ## Solution 2
 ```java
  public boolean hasCycle(ListNode head) {
        // duplicate items might be in LL
       // Set<Integer> vistedSet = new HashSet<>();
        Set<ListNode> visitedSet = new HashSet<>();
        
        ListNode cur = head;
        while(cur != null){
            if(visitedSet.contains(cur)){
                return true;
            }
            visitedSet.add(cur);
            cur = cur.next;
        }
        
        return false;
    }
 ```
