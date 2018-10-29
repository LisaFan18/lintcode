class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length; 
        int n = obstacleGrid[0].length;
        
        int[][] f = new int[m][n]; //f[][]'s default value is 0
        // corner case
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1){
            return 0;
        }
        
        // initialize f[][]
        f[0][0] = 1; // how many unique paths
        for(int c=0; c<n; c++){
            if(obstacleGrid[0][c] == 0){
               f[0][c] = 1; 
            } else {
                break;
            }    
        }
        for(int r=0; r<m; r++){
            if(obstacleGrid[r][0] == 0){
               f[r][0] = 1; 
            } else {
                break;
            }    
        }
        
        for(int i=1; i<m; i++){
          for(int j=1; j<n; j++){
              if(obstacleGrid[i][j] == 0 ){
                f[i][j] = f[i-1][j] + f[i][j-1];   
              }
          }  
        }
        return f[m-1][n-1];
    }
}
