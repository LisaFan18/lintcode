## Summary
1. 这个题目有3种解法：
* bfs: time Complexity O(MN), space complexity: O(min(M,N)) because in worst case all cells in grid are filled with 1, the size of queue can grow up to min(M,N)
* dfs: time Complexity O(MN), space complexity: O(M*N) because in worst case all cells in grid are filled with 1, the dfs can go by M*N steps. 
* union-find: time complexity O(MN) but the union/find operations take O(1) time. Space complexity: O(M*N) because unionFind data structure: parent = new int[row*column];
2. BFS 应该在queue.offer() 进队列时mark it as visited。否则会造成同一个点被offer到queue里多次，进而造成time limit exceeded error.  
以下表为例， cell 5_10 在 visited cell 1时第一次放到queue里，cell 5_10 在visit cell 2时第二次被放到queue里.

|        | 7           |    |   |   
| :-----:|:-----:| :-----:|:-----:|
| 6     | 1_v | **5_10** |     |  
| 4     | 0_v      |   2_v | 8 |
|  | 3      |    9 |    |

3. 2D grid 顺时针遍历右下左上4个neighbors时，采用dx, dy数组简化. 注意：用一层for循环即可完成。
```java
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
        }
```
## Solution (BFS)
### idea
linear scan the 2D grid map, if the cell contains '1', then it's a root node that trigles a BFS.  
* put it into a queue and **mark it as "visited"**  
* iterately search its neighbors, if the neighbor contains '1', put it into the queue and mark it as "visited"  
* until the queue is empty. 
### code
```java
public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        int row = grid.length;
        int column = grid[0].length;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        int counter = 0;
        
        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                if(grid[i][j] == '1'){
                    counter++;
                    qx.offer(i);
                    qy.offer(j);
                    grid[i][j] = '0'; // visited
                    
                    //trigle BFS: space Complexity: O(min(M,N))
                    while(!qx.isEmpty() && !qy.isEmpty()){
                        int cx = qx.poll();
                        int cy = qy.poll();
                        
                        for(int ii=0; ii<4; ii++){
                            int nx = cx + dx[ii];
                            int ny = cy + dy[ii];
                             if(nx>=0 && nx < row && ny>=0 && ny< column && grid[nx][ny] == '1'){
                                qx.offer(nx);
                                qy.offer(ny);
                                grid[nx][ny] = '0'; // visited
                             }
                        }     
                    }
                } // end if
            }
        }
        
        return counter;
    }
```
## Solution (DFS)
### code
```java
 public int numIslands(char[][] grid) {
        // null case
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        int counter = 0;
        for(int i=0; i<grid.length; i++ ){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == '1'){
                    counter++;
                    dfs(grid, i, j);
                }
            }
        }
        return counter;
    }
    
    public void dfs(char[][] grid, int x, int y){
        //stop case
        if(grid[x][y] == '0'){
            return;
        }
       
        grid[x][y] = '0'; // mark as visited
        
        // check its neighbors 
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0 && nx<grid.length && ny>=0 && ny < grid[0].length && grid[nx][ny] == '1'){
                dfs(grid, nx, ny);               
            }
        }
    }
```
## Solution (UnionFind)
### idea
1. makeSet: scan the 2D grid map, if the cell contains '1', make it a set, counter++;   
2. union operation: if current cell contains '1' and its neighboor also contains '1', union(current cell, its neighbor) and counter--;  
3. union operation depends on **find** operation; the return value of find must be parents[i] instead of i.  for example find(1)  

|parent  | 1     | 2      |  3    |   4   | 
| :-----:|:-----:| :-----:|:-----:|:-----:|
| index  | 1     | 2      |  3    | 4     |   

   
```java
     int find(int i){ // with path compression
           if(parent[i] != i){
               parent[i] = find(parent[i]);
           }    
           // return i;  // WRONG, why??
           return parent[i];
        }
```

after the operation find with path compression, we get the below result: 

|parent  | 4     | 4      |  4    |   4   | 
| :-----:|:-----:| :-----:|:-----:|:-----:|
| index  | 1     | 2      |  3    | 4     | 

if return i => return 1; return parent[i]  => return 4.

4. UnionFind 依赖于两个数组 int[] parents; int[] ranks. 

### code (UnionFind)
```java 
class UnionFind {
        int[] parent; 
        int[] rank; 
        int count; 
        
        UnionFind(char[][] grid){
           int row = grid.length;
           int column = grid[0].length;
           count = 0;
           parent = new int[row*column];
           rank = new int[row*column];
         
           for(int i=0; i<row; i++){
               for(int j=0; j<column; j++){
                   if(grid[i][j] == '1'){
                       parent[i*column + j]  = i*column + j;
                       count++; // count grid[i][j] == '1'
                   }
                   rank[i*column + j] = 0; // rank means the number of nodes under "it"
               }
           }
        }
        
        int find(int i){ // with path compression
           if(parent[i] != i){
               parent[i] = find(parent[i]);
           }    
           // return i;  // WRONG, why??
           return parent[i];
        }
        
        void union(int x, int y){
            int rootx = find(x);
            int rooty = find(y);
            
            // x, y are not in the same sets; union x and y
            if(rootx != rooty){
                if(rank[rootx] < rank[rooty]){
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    // if the heights of rootx and rooty are the same
                    if(rank[rootx] == rank[rooty]){
                        rank[rootx] += 1;
                    }
                }
                --count;
            }
            
        }
        
        int getCount(){
            return count;
        }
    }
   
   public int numIslands(char[][] grid) {
        // null case
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        UnionFind uf = new UnionFind(grid);
        int row = grid.length;
        int column = grid[0].length;
        
        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                if(grid[i][j] == '1'){
                    grid[i][j] = '0';
                    // check neighboors
                    if(i-1>=0 && grid[i-1][j] == '1'){
                       uf.union(i*column + j, (i-1)*column+j);  // i*column + j;  2D index -> 1D index   
                    }
                    
                    if(i+1 < row && grid[i+1][j] == '1'){
                       uf.union(i*column + j, (i+1)*column+j);    
                    }
                    
                    if(j-1 >=0 && grid[i][j-1] == '1'){
                       uf.union(i*column + j, i*column + (j-1));    
                    }
                    
                    if(j+1 < column && grid[i][j+1] == '1'){
                       uf.union(i*column + j, i*column + (j+1));    
                    }
                    
                }
            }
        }
        
        return uf.getCount();    
    }
```
