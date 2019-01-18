## Summary
1. [java queue API](https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html) 
add() vs. offer(); remove() vs. poll(); element() vs. peek()  
the former forms(add/remove/element)是basic Collections的operations，throw exception  
the latter forms(offer/poll/peek)是interface Queue的operations，return special value  
    ```java
    public interface Queue<E> extends Collection<E>
    ```
2. 3 ways to implement Queue operations:  
  * based on simple circular array;   
  * based on dynamic circular array;    
  * based on Linked List (most common used) 
  ```java
   Queue<TreeNode> queue = new LinkedList<>();
  ```
 

## 1st idea
1. 人为模拟level order traversal过程： 首先visit root，然后visit root的两个孩子，由近及远， 类似于graph里的BFS。
2. BFS 的implement key points：queue
3. 如何确保queue里哪些nodes 是同一level的呢？ 在queue.poll()之前获取queue.size()

## solution 
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            List<Integer> layer = new ArrayList<>();
            int size = q.size();
            
            for(int i=0; i<size; i++){
                TreeNode node = q.poll();
                layer.add(node.val);
                // treeNode in the next layer EnQueue
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
            }
            ans.add(layer);
        }
        
        // Collections.reverse(ans); // for question 107. Binary Tree Level Order Traversal II
        
        return ans;
    }
}
```
