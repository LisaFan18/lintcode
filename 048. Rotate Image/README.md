## Summary
image dimension: r rows, c columns，将cell (x, y)
1. 上下翻转：(x, y) -> (r-1-x, y)
2. 沿对角线翻转：(x, y) -> (y, x)
3. 顺时针旋转90度：先上下，再交换x, y; 例如第一象限的point A,顺时针旋转90度后到达第二象限。
4. 逆时针旋转90度：先左右，再交换x, y; 例如第一象限的point A,逆时针旋转90度后到达第三象限。

## Solution
```java
     public void rotate(int[][] matrix) {
        //corner case
        if(matrix == null || matrix.length == 0){
            return;
        }
        int r = matrix.length;
        int c = matrix[0].length;
        
        // top and bottom flip
        for(int i=0; i<r/2; i++){
            for(int j=0; j<c; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[r-1-i][j];
                matrix[r-1-i][j] = tmp;
            }
        }
        
        
        // flip along the major diagonal
        for(int i=0; i<r; i++){
            for(int j=i; j<c; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
```
