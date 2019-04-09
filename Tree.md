# Summary
## Top-down recursion (在递去中解决问题)
* 类似于preorder traverse。首先visit root计算出部分答案，把答案作为递归函数的paramter传递给chidlren，从上到下一层一层的build answer
```java
private int answer = Integer.MIN_VALUE;	// 声明一个全局变量，以打擂台的形式得到ans
private void maximum_depth(TreeNode root, int depth) {
    if (root == null) {
        return;
    }
    //在递去中解决问题
    if (root.left == null && root.right == null) {
        answer = Math.max(answer, depth);
    }
    maximum_depth(root.left, depth + 1);
    maximum_depth(root.right, depth + 1);
}
```
## Bottom-up recursion (在归来中解决问题)
* 类似于postorder traverse。首先递归调用childrens,利用chidren的返回值和root value计算answer，从下到上一层一层的build answer
```java
public int maximum_depth(TreeNode root) {
	if (root == null) {
		return 0;                                   
	}
	int left_depth = maximum_depth(root.left);
	int right_depth = maximum_depth(root.right);
   
  //在归来中解决问题
	return Math.max(left_depth, right_depth) + 1;	// return depth of the subtree rooted at root
}
```
3. Traversal 
## Recursion Traversal
### Preorder
```java
   private void preorder(TreeNode root, List<Integer> answer) {
        if (root == null) {
            return;
        }
        answer.add(root.val);          // visit the root first
        preorder(root.left, answer);   // then traverse left subtree
        preorder(root.right, answer);  // finally traverse right subtree
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        preorder(root, answer);
        return answer;
    }
```
### Inorder
```java
  private void inorder(TreeNode root, List<Integer> answer) {
        if (root == null) {
            return;
        }
        inorder(root.left, answer);   // traverse left subtree first
        answer.add(root.val);         // then visit the root
        inorder(root.right, answer);  // finally traverse right subtree
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        inorder(root, answer);
        return answer;
    }
```
### Postorder
```java
   private void postorder(TreeNode root, List<Integer> answer) {
        if (root == null) {
            return;
        }
        postorder(root.left, answer);   // traverse left subtree first
        postorder(root.right, answer);  // then traverse right subtree
        answer.add(root.val);           // finally visit the root
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        postorder(root, answer);
        return answer;
    }
```
## Iterative Traversal
Use stack to simulate recursion process
### Preorder
```java
 public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    //Deque<TreeNode> stack = new ArrayDeque<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode p = root;
    while(!stack.isEmpty() || p != null) {
        if(p != null) {
            stack.push(p);  
            result.add(p.val);  // Visit the root first; 
            p = p.left;         // Then traverse the left subtree
        } else {
            TreeNode node = stack.pop();  
            p = node.right;     // Finally traverse the right subtree
        }
    }
    return result;
}

```
### Inorder
```java
 public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode p = root;
    while(!stack.isEmpty() || p != null) {
        if(p != null) {
            stack.push(p);   
            p = p.left;      // Traverse the left subtree first
        } else {
            TreeNode node = stack.pop();  // All left children are in the stack
            result.add(node.val);  // then visit the root
            p = node.right;    // Finally traverse the right subtree 
        }
    }
    return result;
}
```
### Postorder
```java
public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Integer> result = new LinkedList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode p = root;
    // Reverse the process of preorder
    while(!stack.isEmpty() || p != null) {
        if(p != null) {
            stack.push(p); 
            result.addFirst(p.val);  // Finally visit the root by using addFirst()
            p = p.right;             // Then traverse the right subtree
        } else {
            TreeNode node = stack.pop();
            p = node.left;           // Traverse the left subtree first
        }
    }
    return result;
}
```
# Problems
| Number| Title         | Solution      | Note           | Difficulty    | Tag          |
| ------| ------------- | ------------- | -------------  | ------------- |------------- |
| 098| [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)  | Java  | [Note](https://github.com/LisaFan18/lintcode/tree/master/098.%20Validate%20BST)   | Medium  | Tree, DFS |
| 110| [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/110.%20Balanced%20Binary%20Tree)   | Easy  | Tree, DFS |
| 102| [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/102.%20Binary%20Tree%20Level%20Order%20Traversal)   | Medium  | Tree, BFS |
| 235| [Lowest Common Ancestor of a BST](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/235.%20Lowest%20Common%20Ancestor%20of%20a%20BST)   | Easy  | Tree |
| 236| [Lowest Common Ancestor of a BT](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/236.%20Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree)   | Medium  | Tree |
| 628| [Subtree with Maximum Sum](https://www.lintcode.com/problem/maximum-subtree/description)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/597.%20Subtree%20with%20Maximum%20Average)   | Easy  | Tree, DFS |
| 597| [Subtree with Maximum Average](https://www.lintcode.com/problem/subtree-with-maximum-average/description)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/597.%20Subtree%20with%20Maximum%20Average)   | Medium  | Tree, DFS |
| 200| [Number of Islands](https://leetcode.com/problems/number-of-islands/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/200.%20Number%20of%20Islands)   | Medium  | 2DGrid, DFS/BFS/Union Find|


