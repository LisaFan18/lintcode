## Summary
1. 用divide and conqure的方法。每一棵树和sutree都可以抽象为：  
      root    
    /     \    
left     right    

2. 递归停止条件：到达leaf结点
3. Maximum 使用**global variable** 以打擂台的方式更新。
4. 在归来中解决问题：  
  * 二叉树，Subtree with Maximum Sum: sum > maxSum; maxSum = left_sum + right_sum + root.val
  * 不是二叉树，Subtree with Maximum Average: average = sum/count。这些信息封装到**class ResultType**里，在递归调用里传递。


## Follow up 2: 二叉树，Subtree with Maximum Sum
```java
public class Solution {
   
    // global variable 
    int maxSum = Integer.MIN_VALUE;
    TreeNode result = null;
    
    public TreeNode findSubtree(TreeNode root) {
        dfs(root);
        return result;
    }
    
    public int dfs(TreeNode node){
        if(node == null){
            return 0;
        }
        
       int left_sum = dfs(node.left);
       int right_sum = dfs(node.right);
        
        // update maximum sum
        int sum = left_sum + right_sum + node.val;
        if(result == null || sum > maxSum ){
            maxSum = sum;
            result = node;
        }
        
        return sum;
    }
}
```


## Follow up 1: 不是二叉树，Subtree with Maximum Average
1. 儿子结点保存在List里
2. 注意ansSubtree, maxSubtree 均初始化为null；更新ansSubtree, maxSubtree的条件必须加上：**maxSubtree == null** 规避空指针情况
```java
if (maxSubtree == null || sum*maxSubtree.count > maxSubtree.sum*count) // 用乘法代替除法
```
3. 如果output 排除leaf结点。采用 if(!root.children.isEmpty())

## Solution to Follow up 1
```java
public class MaximumAvgSubtree {
	
	static ResultType maxSubtree = null;
	static Node ansSubtree= null;

	private static Node findMaxAvgSubtree(Node root) {
		if(root == null || root.children.size() == 0) {
			return root;
		}
		
		helper(root);
		return ansSubtree;
	}
	
	private static ResultType helper(Node root) {
		// stop condition 
		if(root == null) {
			return new ResultType(0, 0);
		}
		
		int sum = root.val;
		int count = 1;
		for(Node child : root.children) {
			// 递去
			ResultType info = helper(child);
			// 归来中解决问题
			sum += info.sum;
			count += info.count;
		}
		
		// update the maximum average node
		ResultType cur = new ResultType(sum, count); 
		// exclude leaf node
		if(!root.children.isEmpty()) {
			if (maxSubtree == null || sum*maxSubtree.count > maxSubtree.sum*count) {
				maxSubtree = cur;
				ansSubtree = root;
			}
		}
		return cur;
	}

}

class ResultType {
	int sum;
	int count;
	public ResultType(int s, int n) {
		sum = s;
		count = n;
	}
}

class Node{
	int val;
	ArrayList<Node> children;
	public Node(int val, ArrayList<Node> children) {
		this.val = val;
		this.children = children;
	}
	public Node(int val) {
		this.val = val;
		this.children = new ArrayList<Node>();
	}
}
```
