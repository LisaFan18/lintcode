## Summary
1. 所有left subtree的elements  必须小于等于 root.val 
2. 所有right subtree的elements 必须大于   root.val 
3. 比较当前node.val与全局的upper_limit和lower_limit，而不是node's children. 
4. how to assign the value of upper/lower limit?   
   root.val是其left  subtree的upper_limit;  
   root.val是其right subtree的lower_limit; 
## solution 2 (CORRECT)
```java
   public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        
        return isBSTHelper(root, null, null);  
    }
    
    public boolean isBSTHelper(TreeNode root, Integer lower_limit, Integer upper_limit){
        if ((lower_limit != null) && (root.val <= lower_limit))
            return false;
        if ((upper_limit != null) && (upper_limit <= root.val))
            return false;
        
        boolean isLeftValid = true;
        if(root.left != null){
            isLeftValid = isBSTHelper(root.left, lower_limit, root.val);
        }
        
        if(isLeftValid){
            boolean isRightValid = true;
            if(root.right != null){
                isRightValid = isBSTHelper(root.right, root.val, upper_limit);
            }
            
            return isRightValid;
        }
        
        return false;
    }
```

## solution 1 (WRONG)
based on the defintion of BST, it fails when the input is  [10,5,15,null,null,6,20]
```java
   // 在递去里解决问题
   public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        
        boolean ans = true;
        if(root.left != null){
           ans = ans && (root.val > root.left.val); 
        }
        
        if(root.right != null){
            ans = ans && (root.val < root.right.val);
        }
        
        return ans && isValidBST(root.left) && isValidBST(root.right); 
    }
   // 在归来里解决问题
   public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
       
        boolean isLeftValid = isValidBST(root.left);
        if(!isLeftValid){
            return false;
        }
        boolean isRightValid = isValidBST(root.right);
        if(!isRightValid){
            return false;
        }
        
        boolean ans = true;
        if(root.left != null){
            ans = ans && (root.val > root.left.val);
        }
        
        if(root.right != null){
            ans = ans && (root.val < root.right.val);
        }
        
        return  ans;
    }

```
**why?**
all elements in the right subtree must greater than root value 

**how to solve it?**
keep both upper and lower limits for each node while traversing the tree, and compare the node value not with children values but with these limits. 
