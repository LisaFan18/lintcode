## Summary
1. recursion 的 归来阶段解决问题
2. 合并重复代码

## Idea
根据balanced tree的定义，left subtree and right subtree的height difference 不超过1.   
所以，其本质是compute the height of left/right subtree  

tree问题的输入只有root，而height的计算需要从leaf node开始从bottom to top计算。因此属于在recursion的归来阶段解决问题。

improve：if left/right subtree 已经是unbalanced, 在此即可return false，无需在backtrack 回root时再 compute 然后return false

## Solution 2 (clean)
```java
   public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
    
        return isBalancedHelper(root) != -1; 
        
    }
    
    public int isBalancedHelper(TreeNode root){
        if(root == null){
            return 0;
        }
        
        int left = isBalancedHelper(root.left);
        int right = isBalancedHelper(root.right);
  
        if(left == -1 || right == -1 || Math.abs(left - right) > 1){
            return -1;
        } 
        
        return Math.max(left, right) + 1; 
    }
```

## Solution 1 (long)
```java
   public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
    
        return isBalancedHelper(root) == -1 ? false : true;
        
    }
    
    public int isBalancedHelper(TreeNode root){
        if(root == null){
            return 0;
        }
        
        int left = isBalancedHelper(root.left);
        if(left == -1){
            return -1;
        }
        int right = isBalancedHelper(root.right);
        if(right == -1){
            return -1;
        }
        
        if(Math.abs(left - right) > 1){
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }
```
