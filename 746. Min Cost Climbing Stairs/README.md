## Summary
1. 这个题目虽然是Easy，但自己折腾了很久。两个困难：1）dp初始化；2）dp的status transfer function. 因此衍生出dp[i]有两种不同的表达方式：
  * dp[i] represents the minimum cost **that** we reach the i-th stair.
  * dp[i] represents the minimum cost **before** we reach the i-th stair.
2. 这个题目，站在top那一步时是不需要pay cost的。相当于top在index cost.length的位置。  
3. You only need the previous two elements to compute the current dp[i], use rolling array to save space.
【技巧：先写original解答，再利用old-new数组修改，否则容易搞晕】

## Solution 1
### 子问题    
* **确定状态转移方程**
  * dp[i] 表示到达第 i 步的cost。 到达第i步有两种方式，either come from the i-1th stair or from the i-2th stair  
  * dp[i] =  Min{dp[i-1], dp[i-2]]} + cost[i]  
  * int[] dp = new int[cost.length]
* **initial**   
  start from index 0, dp[0]= cost[0]  
  start from index 1, dp[1]= cost[1]
* **return**   
  return min{dp[top-1], dp[top-2]}
```java
public int minCostClimbingStairs(int[] cost) {
        // null case
        if(cost == null){
            return 0;
        }
        
        int len = cost.length;
         // dp[i] represents the minimum cost that we reach the i-th stair.
        int[] dp = new int[2]; 
        
        dp[0] = cost[0];
        dp[1] = cost[1];
        
        for(int i=2; i<len; i++){
            dp[0] = Math.min(dp[0], dp[1]) + cost[i];
            // swap
            int tmp = dp[0];
            dp[0] = dp[1];
            dp[1] = tmp;
           
        }
      
        // for the top, we can either come from the last stair or from the second-to-last stair.
        return Math.min(dp[0], dp[1]);
        
    }
```

## Solution 2
### 子问题    
* **确定状态转移方程**
  * dp[i] 表示到达第 i 步**之前**的cost。 到达第i步有两种方式，either come from the i-1th stair or from the i-2th stair  
  * dp[i] =  Min{dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]} 
  * int[] dp = new int[**cost.length + 1**]
* **initial**   
  start from index 0, dp[0]= 0  
  start from index 1, dp[1]= 0
* **return**   
  return dp[top]
```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // null case
        if(cost == null){
            return 0;
        }
        
        // dp[i] represents the minimum cost "before" we reach the i-th stair. 
        int[] dp = new int[2]; 
        dp[0] = 0;
        dp[1] = 0; 
               
        for(int i=2; i<=cost.length; i++){  
             dp[0] =  Math.min(dp[0] + cost[i-2], dp[1] + cost[i-1]); 
          // dp[0] =  Math.min(dp[1] + cost[i-2], dp[0] + cost[i-1]);  // wrong
             //swap 
             int tmp = dp[1];
             dp[1] = dp[0];
             dp[0] = tmp;
        }
        
        return dp[1];       
    }
}
```
