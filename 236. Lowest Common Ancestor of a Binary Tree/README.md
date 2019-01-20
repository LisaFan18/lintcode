## Summary
1. 用HashMap<TreeNode, TreeNode> 来保存已经被traverse的node，并作为后续的parent pointer
2. Use Deque over Stack: Deques can also be used as LIFO (Last-In-First-Out) stacks. This interface should be used in preference to the legacy Stack class.  


## idea
由于是binary tree is **without order** property, 所以只能traverse all nodes in BT。  
3 ways (pre/in/post-order) of traverse a tree are a form of **DFS**. 
Its iteration implementation is based on stack.   
或者利用recursion实现

## Solution 2 (recursion)
p, q 两个节点只有3种情况:  
  1. p, q位于LCA的两端，当前root即为LCA  
  2. p是q的ancestor  
  3. q是p的ancestor  
```java
      public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        
        // divide 
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        //conqure
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


## Solution 1 (iteration)
step1: traverse tree，记录下parent pointer    
step2: 把p的 all parents 保存在set里   
step3: 找到第一个q的parents也出现 p‘s parent set里的即为LCA   
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        
        stack.push(root);
        parents.put(root, null);
        
        // terminate if both p and q are found
        while(!(parents.containsKey(p) && parents.containsKey(q))){
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            TreeNode right = node.right;
            
            if(left != null){
                parents.put(left, node);
                stack.push(left);
            }
            if(right != null){
                parents.put(right, node);
                stack.push(right);
            }
        }
        
        // record all ancestors of TreeNode p
        Set<TreeNode> ancestor = new HashSet<>();
        // p is its' parent
        while(p != null){
            ancestor.add(p);
            p = parents.get(p);
        }
        
        // compare and get LCA
        while(!ancestor.contains(q)){
            q = parents.get(q);
        }
        
        return q; 
    }
```
