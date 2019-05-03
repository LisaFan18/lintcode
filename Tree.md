# Summary
## BFS
### when
1. 树or图上的层级遍历 level traverse 例如binary tree level order traversal
2. 由点及面connected component 例如 islands number
3. 简单图(edge = 1)的shortest path. 补充：如果是求最长路径，图可以分层采用DP，不能只能采用DFS
4. 拓扑排序
### how
1. BFS基于queue实现。在java里 Queue<> q = new LinkedList<>(). q.offer(); q.poll()
2. 分层BFS 多一层for 循环。 int size = q.size(); for(int i=0; i<size; i++){...}
3. Bi-directional BFS: 如果起点和终点都知道，利用双向BFS来加速。例如word ladder
### complexity
1. Time 通常为O(n) n为node数，each node in the tree is added to the queue only once. 
2. Space 通常为O(w) w为树的宽度。Queue will contain all the nodes in one layer of binary tree. In worse case, for a full binary tree, the leaf layer has n/2 nodes. Thus, space complexity is O(n) 

## DFS
### when
1. 二叉树上的两种情况：求max/min/sum值，求路径问题；二叉树结构的变化例如flatten binary tree
2. bst上的问题
### how
1. 遍历法：有递归和非递归两种实现方式。非递归就是手动模拟stack的过程
2. 分治合法：
### complexity
1. Time 通常为O(n) n为node数，通常每个node都要被search 一次
2. Space 通常为O(h) h is the height of the tree。because of recursion, O(h) function calls are placed on the stack. In the worst case, O(h) is O(n)

## Traverse 
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
| 112 | [Path Sum](https://leetcode.com/problems/path-sum/)| Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/112.%20Path%20Sum) |  Easy  | Tree, preorder |
| 098| [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)  | Java  | [Note](https://github.com/LisaFan18/lintcode/tree/master/098.%20Validate%20BST)   | Medium  | Tree, DFS |
| 110| [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/110.%20Balanced%20Binary%20Tree)   | Easy  | Tree, DFS |
| 102| [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/102.%20Binary%20Tree%20Level%20Order%20Traversal)   | Medium  | Tree, BFS |
| 235| [Lowest Common Ancestor of a BST](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/235.%20Lowest%20Common%20Ancestor%20of%20a%20BST)   | Easy  | Tree |
| 236| [Lowest Common Ancestor of a BT](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/236.%20Lowest%20Common%20Ancestor%20of%20a%20Binary%20Tree)   | Medium  | Tree |
| 628| [Subtree with Maximum Sum](https://www.lintcode.com/problem/maximum-subtree/description)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/597.%20Subtree%20with%20Maximum%20Average)   | Easy  | Tree, DFS |
| 597| [Subtree with Maximum Average](https://www.lintcode.com/problem/subtree-with-maximum-average/description)  | Java | [Note](https://github.com/LisaFan18/lintcode/tree/master/597.%20Subtree%20with%20Maximum%20Average)   | Medium  | Tree, DFS |
| 104| [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)  | Java | [Note]()   | Easy  | Traversal, Divide and Conqure |

