# Backtrack
## what's backtrack

Below is borrowed from [geeksforgeeks](https://www.geeksforgeeks.org/backtracking-algorithms/)  
Backtracking can be defined as a general algorithmic technique that considers searching every **possible combination** in order to solve a **computational problem**.

Backtracking algorithms are generally **exponential** in both time and space 

Backtracking is an algorithmic-technique:  
  * solving problems **recursively** by trying to build a solution **incrementally, one piece at a time**,  
  * removing those solutions that fail to satisfy the constraints of the problem at any point of time (by time, here, is referred to the time elapsed till reaching any level of the search tree).

## when to use backtrack?
There are three types of problems in backtracking . 
  * Decision Problem – In this, we search for a feasible solution.  
  * Optimization Problem – In this, we search for the best solution.  
  * Enumeration Problem – In this, we find all feasible solutions.

## how to use backtrack?
Pseudo Code for Backtracking (Recursive)
```java
void findSolutions(n, other params) :
    if (found a solution) :
        solutionsFound = solutionsFound + 1;
        return

    for (val = first to last) :
        if (isValid(val, n)) :
            applyValue(val, n);
            findSolutions(n+1, other params);
            removeValue(val, n);
```
神贴 [A general approach](https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)) to backtracking questions in Java (Subsets, Permutations, Combination Sum, Palindrome Partioning)

## 枚举型DFS
DFS 模拟嵌套for loop。而且for loop的层数是variable 


# Problems
| Number| Title         | Solution      | Note           | Difficulty    | Tag          |
| ------| ------------- | ------------- | -------------  | ------------- |------------- |
| 046| [Permutations](https://leetcode.com/problems/permutations/)  | [Java](https://github.com/LisaFan18/lintcode/blob/master/046.%20Permutations/REAMDE.md)  | [Note](https://github.com/LisaFan18/lintcode/blob/master/046.%20Permutations/REAMDE.md)   | Medium  | Backtrack |
| 047| [Permutations II](https://leetcode.com/problems/permutations-ii/)  | [Java](https://github.com/LisaFan18/lintcode/tree/master/047.%20Permutations%20II)  | [Note](https://github.com/LisaFan18/lintcode/tree/master/047.%20Permutations%20II)   | Medium  | Backtrack |
| 077| [Combinations](https://leetcode.com/problems/combinations/)  | [Java](https://github.com/LisaFan18/lintcode/tree/master/077.%20Combinations)  | [Note](https://github.com/LisaFan18/lintcode/tree/master/077.%20Combinations)   | Medium  | Backtrack |
