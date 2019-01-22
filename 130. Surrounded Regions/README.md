## Summary
1. applications of BFS：  
  * Finding all connected components in a graph/matrix
  * Finding the shortest path between two nodes
2. in matrix, use dx, dy to traverse matrix easily. 
3. the key thing of BFS is to use **queue**

## Idea
1. 正向思维，~~直接判断‘O’是否被'X'需要四个方向上搜索，如果相邻位置也是‘O’如何记录？~~. 将被‘X’包围的‘O’标记为‘B’，然后遍历整个matrix改为‘X’，
但matrix是从edge 的cell开始遍历，并不是从中间开始的（行不通，反向思维）
2. 反向思维：matrix 里的‘O’分为两类：  
  * 边界上的‘O’。如果从边界上的‘O’出发，找到所有相邻的‘O’并把他们标记为‘B’，那么matrix里剩下的‘O’就是被‘X’包围的。
  * 非边界上的 ‘O’
3. 如何找到所有相邻的‘O’呢？BFS
 
## Solution
```java
   public void solve(char[][] board) {
        // corner case
        if(board == null){
            return;
        }
        
        int r = board.length;
        if(r == 0){
            return;
        }
        int c = board[0].length;
        
        //do BFS start from the top and bottom rows
        for(int i=0; i<c; i++){
            bfs(board, 0, i);
            bfs(board, r-1, i);
        }
        
        //do BFS start from the left and right columns
        for(int i=0; i<r; i++){
            bfs(board, i, 0);
            bfs(board, i, c-1);
        }
   
        // change 'B' -> 'O'; change 'O' to 'X' 
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                
                if(board[i][j] == 'B'){
                    board[i][j] = 'O';
                }
            }
        }
        
    }
    
    // Find all connected components of 'O' 
    public void bfs(char[][] board, int x, int y){
        if(board[x][y] != 'O'){
            return;
        }
        
        int r = board.length; // rows
        int c = board[0].length; // columns
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0}; 
        
        //BFS: queue
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.offer(x);
        qy.offer(y);
        
        //mark current cell by 'B' (BFS)
        board[x][y] = 'B'; 
        
        while(!qx.isEmpty()){
            int cx = qx.poll(); 
            int cy = qy.poll();
            
           // check its' neighbors, if the neighbor is 'O', mark it as 'B'
            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(nx>=0 && nx<r && ny>=0 && ny<c && board[nx][ny] == 'O'){
                    board[nx][ny] = 'B';
                    qx.offer(nx);
                    qy.offer(ny);
                }
            }
         
        }       
    }
```
