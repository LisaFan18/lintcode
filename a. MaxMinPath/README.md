## Summary
to do

## Question
* Given a matrix, start from left top corner to right bottom corner. Only right and down directions. Compute the maximum of minium of all possible paths.  
* Example:   
  [8 4 7]  
  [6 5 9]  
* all possilbe paths:  
   * 8-4-7-9 min: 4  
   * 8-4-5-9 min: 4  
   * 8-6-5-9 min: 5    
return: 5  

## Idea of DP
1. to do. 没想明白，以后补充

## Solution of DP
```java

  public static int maxMinPathDP(int[][] matrix) {
		int column = matrix[0].length;
		
		// status initialize
		int[] maxPath = new int[column];
		maxPath[0] = matrix[0][0];
		for (int j = 1; j < column; j++)
			maxPath[j] = Math.min(maxPath[j - 1], matrix[0][j]);
		
		// status transfer
		for (int i = 1; i < matrix.length; i++) {
			maxPath[0] = Math.min(maxPath[0], matrix[i][0]); //i-1, i => move down
			for (int j = 1; j < column; j++)
				maxPath[j] = Math.min(Math.max(maxPath[j - 1],maxPath[j]), matrix[i][j]); // j-1, j => move right
		}
		
		return maxPath[column - 1];
	}
```

## Idea of DFS
1. 这是一个遍历all possible paths，从起点出发到终点的生存一条path的过程中计算minimun，path与path之间打擂台的方式决出maximum。  
可以用**DFS**来解决。  
2. 停止条件：
 * 1）x, y 跑到 matrix外面去了，直接return；  ==》 void dfs(...)
 * 2）到达destination了，一条完整的path产生。计算一次maximun 
3. 在递去中解决问题还是归来中？  
 * 由于在访问下一个结点前，要compare 当前结点和minimum ==》 在递去中解决问题。  
4. 参数问题  
 * 多层dfs之间要compare minimum ==》 minimun 作为参数 ==》 void dfs(..., min,...)  
 * 用dx, dy来模拟move in right and down directions  ==》 void dfs(int[][] matrix, min, int x, int y)  
## Solution of DFS
```java
  private static int maxMinPath(int[][] matrix) {
		row = matrix.length;
		column = matrix[0].length;
		gmin = Integer.MAX_VALUE;
		gmax = Integer.MIN_VALUE;
		dfs(matrix, gmin, 0, 0);
		return gmax;
	}

	private static void dfs(int[][] matrix, int local_min, int x, int y) {
		// condition 1
		if (x >= row || y >= column) {
			return;
		}
		// condition 2: reach destination
		if (x == row - 1 && y == column - 1) {
			local_min = Math.min(local_min, matrix[x][y]);
			gmax = Math.max(gmax, local_min);
			return;
		}
		local_min = Math.min(local_min, matrix[x][y]);
		dfs(matrix, local_min, x, y + 1); // move right
		dfs(matrix,  local_min, x + 1, y); // move down
	}
```
