## Summary
1. BFS 利用1个queue 遍历所有结点  
2. BFS 利用2个queues 一层一层的往外遍历。层数即the number of path. 计算 shortest path。  


## Question of delomition robot 
1. 这个题目是[leetcode 505 the maze II](https://leetcode.com/problems/the-maze-ii/)的 similar question:    
1 表示 flat areas，0 表示trench，不能访问。9 表示obstracle即destination。    
由于 0 表示trench，不能访问， 可以将访问过的 matrix[x][y] == 1 直接udpate为matrix[x][y] == 0 表示 visited。  
省去显示声明int[][] visited
2. 隐约记得可以只利用一个queue也能计算shortest path，**待补充**。

## Solution to delomition robot （count steps）
```java
public static int mazeShortestPath(int[][] maze) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return -1;
		}
		int row = maze.length;
		int column = maze[0].length;

		// right, down, left, up
		int[] dx = new int[] { 0, 1, 0, -1 };
		int[] dy = new int[] { 1, 0, -1, 0 };
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		qx.offer(0);
		qy.offer(0);

		int layer = 0;
		while (!qx.isEmpty() && !qy.isEmpty()) {
			Queue<Integer> tx = new LinkedList<>();
			Queue<Integer> ty = new LinkedList<>();
			// visit all nodes in the same layer
			while (!qx.isEmpty() && !qy.isEmpty()) {
				int cx = qx.poll();
				int cy = qy.poll();

				// if it's destination
				int cell = maze[cx][cy];
				if (cell == 9) {
					return layer;
				}
				// 0 means trench, robot can't pass it; similar visited.
				// 1 means flat, robot can pass it; similar unvisited.
				if (cell == 1) {
					maze[cx][cy] = 0; // unvisited -> visited
					// iterate its' neighbors
					for (int i = 0; i < 4; i++) {
						int nx = cx + dx[i];
						int ny = cy + dy[i];
						// Note: maze[nx][ny] != 0 instead of != 1 as 9 will be excluded.
						if (nx >= 0 && nx < row && ny >= 0 && ny < column && maze[nx][ny] != 0) {
							tx.offer(nx);
							ty.offer(ny);
						}
					}
				}
			}
			layer++; // step count the number of cells visited. but we want the length of path.
			qx = tx;
			qy = ty;

		}
		return -1;
	}
```

## Solution to delomition robot （count cellNumber）
```java
public static int bfs(int[][] maze) {
   if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return -1;
		}
		int row = maze.length;
		int column = maze[0].length;
		
		// right, down, left, up
		int[] dx = new int[] {0, 1, 0, -1};
		int[] dy = new int[] {1, 0, -1, 0};
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		qx.offer(0);
		qy.offer(0);
		
		int cellNum = 0;
		while(!qx.isEmpty() && !qy.isEmpty()) {
			int cx = qx.poll();
			int cy = qy.poll();
			
			// if it's destination 
			int cell = maze[cx][cy];
			if( cell == 9) {
				return cellNum;
			} 
			// 0 means trench, robot can't pass it; similar visited. 
			// 1 means flat,   robot can pass it; similar unvisited. 
			if (cell == 1) {
				maze[cx][cy] = 0; // unvisited -> visited
				// iterate its' neighbors
				for(int i=0; i<4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					// Note: maze[nx][ny] != 0 instead of != 1 as 9 will be excluded. 
					if(nx>=0 && nx < row && ny>=0 && ny < column && maze[nx][ny]  != 0 ) {
						qx.offer(nx);
						qy.offer(ny);
					}
				}
			}
			cellNum++; // step count the number of cells visited. but we want the length of path. 
			
		}
		return -1;
	}
```
