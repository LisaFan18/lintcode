## 思考
这个题目是unique path的follow up。有些网格有障碍。因此initialize f[][]有区别

1. 在初始化第一行和第一列时要注意。要注意： 应该用break而不是continue
```java
for(int c=0; c<n; c++){
    if(obstacleGrid[0][c] == 0){
       f[0][c] = 1; 
    } else {
        // continue;     // WRONG as when obstacleGrid[0][3] = 1, f[0][4...n] must be 0
        break;
    }    
}
```

2. 状态转移方程： f[i][j] = f[i-1][j] + f[i][j-1] 实现方式应该遵从“子问题”

```java
// method 1 f[i][j] = f[i-1][j] + f[i][j-1]
for(int i=1; i<m; i++){
    for(int j=1; j<n; j++){
        if(obstacleGrid[i][j] == 0 ){
          f[i][j] = f[i-1][j] + f[i][j-1]; 
        }
    }  
  }
```
下面这种实现方式虽然final result正确，但是没有遵循“子问题”的思考方式
```java
// method 2 f[i][j] = f[i-1][j] + f[i][j-1]
for(int i=1; i<m; i++){
  for(int j=1; j<n; j++){
    if(obstacleGrid[i-1][j] == 0 ){
      f[i][j] += f[i-1][j];   
    }
    if(obstacleGrid[i][j-1] == 0 ){
      f[i][j] += f[i][j-1];   
    }
  }   
}
```
