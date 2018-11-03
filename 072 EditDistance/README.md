## summary
1. subproblem 按照3 种operations 划分, 每次子问题规模减少1。所以用dp,不用递归
2. int[][] f = new int[word1.length + 1][word2.length + 1] 因为字符串数组下标从[0] 开始。
   f[0][0] = 0, f[1][1] 需要判断 word1[1-0] 与 word2[1-0] 是否相等

```java
class Solution {
    public int minDistance(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int r = s1.length;
        int c = s2.length; 
        int[][] f = new int[r + 1][c + 1];
        
        f[0][0] = 0;
        # since f.length = r + 1, thus i <=r 
        for(int i=1; i<=r; i++){
            f[i][0] = i;
        }
        for(int j=1; j<=c; j++){
            f[0][j] = j;
        }
        
        for(int i=1; i<=r; i++){
            for(int j=1; j<=c; j++){
                # note! array index starts at 0, thus s1[i - 1] == s2[j - 1], NOT s1[i] == s2[j]
                if(s1[i - 1] == s2[j - 1]) {
                    f[i][j] = Math.min(Math.min(f[i-1][j] + 1, f[i][j-1] + 1), f[i-1][j-1]);
                } else {
                    f[i][j] = Math.min(Math.min(f[i-1][j] + 1, f[i][j-1] + 1), f[i-1][j-1] + 1);
                }
            }
        }
        
        return f[r][c]; 
    }
}
```
