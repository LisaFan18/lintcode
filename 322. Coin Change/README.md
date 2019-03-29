## 思考
1. 从fewest number 得出该题目为最值型dp问题。

Input: coins = [1, 2, 5], amount = 11  
Output: 3   
Explanation: 11 = 5 + 5 + 1  

2. **solution3 3/29/2019 update**   
* since 题目要求计算fewest，状态转移方程一定有Math.min()，那么初始值应为Integer.MAX_VALUE。
* 注意dp[i]更新条件，避免Integer.MAX_VALUE+1造成溢出。
* 求解子问题，思考最后一步的情况
* 题目要求不能拼凑时返回-1，可是在中间计算过程求fewest要用到Integer.MAX_VALUE。需要在return加一句判断  return dp[amount] == Integer.MAX_VALUE ? -1: dp[amount];

## summary
1. 确定状态  
   面值i的硬币最少由f[i]枚coins构成  
   1.1 最后一步 f[11] = min(f[11 - 1] + 1,f[11 - 2] + 1,f[11 - 5] + 1)  
   1.2 化为子问题 本来要计算11，现在改为计算11-1， 11-2，11-5 三个子问题  
2. 状态转换方程  
    f[i] = min(f[i - 1] + 1,f[i - 2] + 1,f[i - 5] + 1)  
3. 初始条件和边界条件  
   f[0] = 0,  i - coins[j] >= 0
4. 计算顺序  
   f[0] -> f[i]  

## solution 3 (cleaner)
```java
public int coinChange(int[] coins, int amount) {
        // corner case
        if(coins == null || coins.length ==0 || amount < 0){
            return -1;
        }
        
        //dp[i] represents the fewest number that amount i can be made up
        int[] dp = new int[amount + 1];   
        dp[0] = 0;
        
        for(int i=1; i<=amount; i++){
            // enumerate amount of the last one coin 
            dp[i] = Integer.MAX_VALUE;
            for(int coin: coins){
                if(i - coin>=0 && dp[i-coin] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }
         
        return dp[amount] == Integer.MAX_VALUE ? -1: dp[amount];
    }
```
## solution 2
f[i] = -1 表示 no combination 拼出面值i

注意，f[i]更新的条件！！
```java
class Solution {
    public int coinChange(int[] coins, int m) {
        int[] f = new int[m + 1]; // 1. amount i can be made up by f[i] combinations    
        f[0] = 0; // initialization
        
        for(int j=1; j<=m; j++){ 
            f[j] = -1; 
            for(int k=0; k<coins.length; k++){
                int pre = j-coins[k];
                if(pre >=0 && f[pre] != -1 )
                   if( f[j] == -1 || f[j] > f[pre] + 1)
                      f[j] = f[pre] + 1; 
            }
        }  
        return f[m]; 
    }
}
```

## [solution 1](https://github.com/LisaFan18/lintcode/blob/master/322.%20Coin%20Change/solution1.java)
f[i] = Integer.MAX_VALUE 表示 no combination 拼出面值i
