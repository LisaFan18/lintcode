# Recursive Method
## idea
1. What's recursive problem? it can be build off of **sub-problems**.  
   * recursive solution are built off solutions to sub-problems.   
   * compute f(n) by simply change(add or remove) the solution for f(n-1).   
2. Bottom-up Recursion  (common used)
   key: how to build the solution for one case off the previous case
3. Top-down Recursion  (more complex)  
   key: be careful of overlap between the cases

## Iterative vs. Recursion 
   * recursion is space inefficient. each recursion call adds a new layer to the stack. If the algorithm has O(n) recursive calls, then it uses O(n) memory
   * All recursive code can be implemented iteratively, although sometimes the code to do so is much more complex. 
   * Recursion's prons/cons: **easy to implement, space inefficient**
## Complexity
[Time and space complexity of Recursive Algorithm](https://stackoverflow.com/questions/43298938/space-complexity-of-recursive-function)
 * Time: f = f(n-1) + f(n-2) => T(n) = 2T(n-1) => O(2^n). To build a **recursion tree**, since each node has 2 branches and we have n total levels, our total number of nodes is 2^n making our time complexity O(2^n).
 * Space: To generalize, a recursive function's memory complexity is **O(recursion depth)**. Memory complexity is determined by the number of return statements 
 because each function call will be stored on the program stack. 
 
 # Problems 
 | Number| Title         | Solution      | Note           | Difficulty    | Tag          |
| ------| ------------- | ------------- | -------------  | ------------- |------------- |
| 155| [Min Stack](https://leetcode.com/problems/min-stack/)  | Java  | [Note](https://github.com/LisaFan18/lintcode/tree/master/155.%20Min%20Stack)   | Medium  | Two stacks or Stack with the difference |
