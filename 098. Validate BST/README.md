## Summary
1. BST's order property: 比较当前node.val与**全局**的upper_limit和lower_limit，而不是node's children. 
* 所有left subtree的elements  必须小于等于 root.val 
* 所有right subtree的elements 必须大于   root.val 
2. how to assign the value of upper/lower limit?   
   root.val是其left  subtree的upper_limit;  
   root.val是其right subtree的lower_limit; 
   
## solution 1 (Min/Max, One Error)
* Algorithm: iterate through the tree and pass down the Min/Max value. When branch left, the max gets updated; when branch right, the min gets updated.
* Time: O(N) as all N nodes is touched one time. Besides, it's the best algorithm since any algorithm must touch all nodes.
* Space: O(logN) on a blanced tree as there are up to O(logN) recursive calls on the stack.
* case: [2147483647] couldn't pass. Solution: don't use  Integer.MIN_VALUE, Integer.MAX_VALUE as initial value 
```java
// Min/Max Solution
// BST precise definition: all left nodes <= root && root < all right nodes
  public boolean isValidBST(TreeNode root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);  
   }
    
   public boolean checkBST(TreeNode root, int min, int max) {
	// null case
	if(root == null) {
		return true;
	}
	// base case 
	if(root.val <= min || root.val > max) {
		return false;
	}
	
	if(!checkBST(root.left, min, root.val) ||
	   !checkBST(root.right, root.val, max)	) {
		return false;
	}
	
	return true;
}
```

## solution 2a_updated (CORRECT)
```java
   public boolean isValidBST(TreeNode root) {
        //return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // case: [2147483647] couldn't pass
        return helper(root, null, null);
    }
    
    private boolean helper(TreeNode root, Integer min, Integer max){
        // null case
        if(root == null){
            return true;
        }
        
        // base case: 在递去中解决问题
        if(min != null && root.val <= min){
            return false;
        }
        
        if(max != null && root.val >= max){
            return false;
        }
        
        boolean isLeftValid = helper(root.left, min, root.val);
        // 在归来中解决问题, 减少不必要的check
        // 如果subtree已经unbalanced, 当前root一定unbalanced
        if(!isLeftValid){
            return false;
        }
       
        boolean isRightValid = helper(root.right, root.val, max);
        if(!isRightValid){
            return false;
        }
          
        return true;
    }
```
   
## solution 2b (CORRECT)
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
