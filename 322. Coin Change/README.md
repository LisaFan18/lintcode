## 思考
从fewest number 得出该题目为最值型dp问题。

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

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
