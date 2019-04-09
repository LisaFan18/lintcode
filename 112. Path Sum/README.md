## Summary
1. path: 是指从root到leaf的一条路径
2. 递归调用考虑两个问题：子问题，停止条件
2. top-down recursive策略：
* stop condition: 到达叶子结点。if(curr.left == null && curr.right == null) return sum == 0
* 当前level能构造部分answer。 利用sum - curr.val作为参数传递到下一层。利用加法也可以，但需要多增加1个参数
* tree有left，right两个分支，所以递去时有两种可能，final结果由左右两个分支进行||操作合并得到。

## recursion
```java
   public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        
        sum = sum - root.val;
        //stop condition: reach the leaf node
        if(root.left == null && root.right == null){
            return sum == 0;
        }
        
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }  
```
## iterative
1. 用stack来simulate recursive process, 由于递归函数有两个parameters，因此需要用两个stack。
2. 注意，iterative停止搜索的条件是，已经找到了一条path。而不是recursion停止条件：到达leaf node
```java
   public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(sum);
        
        while(!nodeStack.isEmpty()){
            TreeNode node = nodeStack.pop();
            Integer topSum = sumStack.pop();
            
            // reach the leaf node and find one path
            if(node.left == null && node.right == null && topSum == node.val){
                return true;
            }
            
            // WRONG
            // if(node.left == null && node.right == null){
            //     return topSum == node.val;
            // }
            
            // preorder traverse
            if(node.right != null){
                nodeStack.push(node.right);
                sumStack.push(topSum - node.val);
            }
            
            if(node.left != null){
                nodeStack.push(node.left);
                sumStack.push(topSum - node.val);
            }
        }
        
       return false; 
    }
```
