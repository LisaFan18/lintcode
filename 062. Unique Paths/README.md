## summary
1. 题目问 How many possible unique paths are there? 坐标型 + 计数型dp：
 * 坐标型 state f[i][j]
 * 计数型，state transfer 用sum
2. 注意：
 * f[0][0] = 1 而不是 0， 通过带入法，求f[0][1], f[1][0], f[1][1]。如果f[0][0] = 0，利用sum，整个棋盘的值都是0
 * corner case f[0][i], f[i][0] 先计算，避免主体计算时，用if做很多边界条件的计算。

## solution
```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        f[0][0] = 1; 
        for(int r=0; r<m; r++){
            f[r][0] = 1;
        }
        for(int c=0; c<n; c++){
            f[0][c] = 1;
        }
        
        for(int r=1; r<m; r++){
            for(int c=1; c<n; c++){
                f[r][c] = f[r-1][c] + f[r][c-1];
            }
        }
        
        return f[m-1][n-1];
    }
}
```
