## Summary
1. BFS 应该在queue.offer() 进队列时mark it as visited。否则会造成同一个点被offer到queue里多次，进而造成time limit exceeded error.  
以下表为例， cell 5_10 在 visited cell 1时第一次放到queue里，cell 5_10 在visit cell 2时第二次被放到queue里.

|        | 7           |    |   |   
| :-----:|:-----:| :-----:|:-----:|
| 6     | 1_v | **5_10** |     |  
| 4     | 0_v      |   2_v | 8 |
|  | 3      |    9 |    |


2. 这个题目有3种解法：bfs, dfs, union-find

## Solution (BFS)
linear scan the 2D grid map, if the cell contains '1', then it's a root node that trigles a BFS.  
* put it into a queue and **mark it as "visited"**  
* iterately search its neighbors, if the neighbor contains '1', put it into the queue and mark it as "visited"  
* until the queue is empty. 

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
                    grid[i][j] = 'B'; // visited
                    
                    //trigle BFS
                    while(!qx.isEmpty() && !qy.isEmpty()){
                        int cx = qx.poll();
                        int cy = qy.poll();
                        
                        for(int ii=0; ii<4; ii++){
                            int nx = cx + dx[ii];
                            int ny = cy + dy[ii];
                             if(nx>=0 && nx < row && ny>=0 && ny< column && grid[nx][ny] == '1'){
                                qx.offer(nx);
                                qy.offer(ny);
                                grid[nx][ny] = 'B'; // visited
                             }
                        }     
                    }
                } // end if
            }
        }
        
        return counter;
    }
```
