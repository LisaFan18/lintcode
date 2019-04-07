## Summary
1. 这个题目跟[coin change](https://github.com/LisaFan18/lintcode/tree/master/322.%20Coin%20Change)思路一样
2. 子问题，考虑最后一步：
* 最后一步可能的选择是所有比n小的perfect number。因此用for-loop来列举all possible 选择类似于可选的coin面值。
* since 题目要求计算fewest，状态转移方程一定有Math.min()，那么初始值应为Integer.MAX_VALUE。

## solution (dp)
```java
  public int numSquares(int n) {
        int[] f = new int[n+1];
        f[0] = 0;
        
        for(int i=1; i<=n; i++){
            f[i] = Integer.MAX_VALUE;
            // 枚举最后一步可能的面值
            for(int j=1; j*j<=i; j++){
                f[i] = Math.min(f[i], f[i-j*j] + 1);
            }
        }
        
        return f[n];
    }
```

## solution (bfs)
感觉不好理解，to do
