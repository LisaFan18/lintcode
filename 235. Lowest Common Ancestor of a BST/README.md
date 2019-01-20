## Summary  
1. clarification: p,q一定在bst里吗？ all elements in BST are unique? 
2. 利用BST的order属性，分情况讨论：   
  2.1 如果 p 和 q 位于当前root的两边，当前root就是LCA  
  2.2 如果 p,q 小于root.val， LCA 位于left subtree，利用2.1递归查找，直到找到为止  
  2.3 如果 p,q 大于root.val， LCA 位于right subtree，利用2.1递归查找，直到找到为止

## 1st idea
第一直觉，先把p和q在tree里的位置找到，再backtrack回去找common ancestor。由于tree node并没有link to its parents，把沿途的node都记录下来？
感觉有点复杂了。  

换种思路： 边定位p，q边找LCA. 由于BST order的属性，利用binary search的思路，和root（midpoint）compare。  
In summary, 看两个值在root的哪边： 
- 两个值都在左边，则LCA在左边  
- 两个值都在右边，则LCA在右边  
- 一个在左一个在右，则说明LCA就是当前的root节点。
## solution 1 (recursion)
把bst当作binary tree看待  
```java
   public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {  
        if(root == null || root == p || root == q){
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right != null){
            return root;
        }
        if(left != null){
            return left;
        }
        if(right != null){
            return right;
        }
        
        return null;
    }
```
## solution 2 (improved recursion)
solution 2 利用bst的sorted 的property，减少不必要的search
```java
   public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if(root == null){
            return root;
        }
        
        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        
        return root; // root is the LCA when p and q are different sides of root
    }
```

## solution 3 (iteration)
类似于binary search， 跟midpoint/root进行compare。
```java
   public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {  
        TreeNode node = root;
        
        // traverse the tree
        while(node != null){
            if(p.val < node.val && q.val < node.val){
                node = node.left;
            } else if (p.val > node.val && q.val > node.val){
                node = node.right;
            } else {
                return node;
            }
        }
        
        return null; 
    }
```
