## Summary

### what's backtrack

The below is borrowed from [geeksforgeeks](https://www.geeksforgeeks.org/backtracking-algorithms/)
Backtracking can be defined as a general algorithmic technique that considers searching every **possible combination** in order to solve a **computational problem**.

Backtracking algorithms are generally **exponential** in both time and space 

Backtracking is an algorithmic-technique:  
  * solving problems **recursively** by trying to build a solution **incrementally, one piece at a time**,  
  * removing those solutions that fail to satisfy the constraints of the problem at any point of time (by time, here, is referred to the time elapsed till reaching any level of the search tree).

### when to use backtrack?
There are three types of problems in backtracking . 
  * Decision Problem – In this, we search for a feasible solution.  
  * Optimization Problem – In this, we search for the best solution.  
  * Enumeration Problem – In this, we find all feasible solutions.

### how to use backtrack?
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

## Solution
```java
public List<List<Integer>> permute(int[] nums) {
        // global variable
        List<List<Integer>> ans = new ArrayList<>();
        //corner case
        if(nums == null){
            return ans;
        }
        
        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), ans);
        return ans;
    }
    
    public void dfs(int[] nums, boolean[] visited, 
                     List<Integer> permutation,
                     List<List<Integer>> ans ){
        // terminate condition
        if(nums.length == permutation.size()){
            ans.add(new ArrayList<Integer>(permutation));
            return;
        }
        
        //expand 
        for(int i=0; i<nums.length; i++){
            if(visited[i]){
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums,visited, permutation, ans);
            
            //backtrack
            permutation.remove(permutation.size() - 1); // always remove the last in
            visited[i] = false;
        }
        
    }
```
