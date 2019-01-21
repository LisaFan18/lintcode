## Summary
1. 枚举型dfs模拟嵌套for循环； 循环layers依赖于输入
2. dfs函数有2处return的地方：1） 满足 recursion condition； 2）执行到函数的最后一行

## Solution
```java
   public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        
        dfs(n, k, 1, combination, ans);
        return ans;
    }
    
    public void dfs(int n, int k, int start, 
                    List<Integer> combination,
                    List<List<Integer>> ans){
        
        // terminate
        if(combination.size() == k){
            ans.add(new ArrayList(combination));
            return; // return place 1
        }
        
        for(int i=start; i<=n; i++){
            combination.add(i); // 在递去中解决问题
            dfs(n, k, i + 1, combination, ans);
            combination.remove(combination.size() - 1); // 在归来中解决问题
        }
        // return place 2
    }
```
