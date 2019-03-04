## Summary
1. Priority自定义Comparator，多层compare方式
2. int compare(Object o1, Object o2) Returns a **negative integer**, zero, or a positive integer as the first argument is **less than**, equal to, or greater than the second.  
3. PriorityQueue<**int[]**> maxHeap 
4. brute force method: 1) 把所有distance算出来并排序，得到dist[K-1]作为threshold； 2） iterate all over points，distance < threshold的留下。优点：保留了points的原始顺序，缺点：Time complexity: O(n*logn) 

## Question on LeetCode
https://leetcode.com/problems/k-closest-points-to-origin/

## Solution to LeetCode
```java
    public int[][] kClosest(int[][] points, int K) {
        // keep closest, thus use maxHeap instead of minHeap
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> p2[0]*p2[0] + p2[1]*p2[1] - p1[0]*p1[0] - p1[1]*p1[1]);
        
        // build maxHeap
        for(int[] p : points){
            maxHeap.offer(p);
            if(maxHeap.size()>K){
                maxHeap.poll();
            }
        }
        
        // retrieve ans
        int[][] ans = new int[K][2];
        while(!maxHeap.isEmpty()){
            ans[--K] = maxHeap.poll();
        }
        
        return ans;
    }
```

## Question on LintCode 
https://www.lintcode.com/problem/k-closest-points/description 
## Solution to Lintcode 
```java
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // step 1: define the Comparator
        // 多层compare方式，用anonymous class instead of lamada function 
        // 以distance由远到近排序,当 maxHeap 元素大于k时，pop出堆顶元素，留下closest k points
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k, new Comparator<Point>() {
           @Override
           public int compare(Point p1, Point p2){
              int diff =  calculateDist(p2, origin) - calculateDist(p1, origin); 
              if(diff == 0){
                  diff = p2.x - p1.x;
              }
              if(diff == 0){
                  diff = p2.y - p1.y;
              }
              return diff;
           }
        });
       
       // step 2: iterate each point and put it into maxHeap
        for(int i=0; i<points.length; i++){
            maxHeap.offer(points[i]);
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }
        
        // step 3: get the result
        Point[] ans = new Point[k];
        int i = k;
        while(!maxHeap.isEmpty()){
            ans[--i] = maxHeap.poll();
        }
        
        return ans;
    }
    
    public int calculateDist(Point s, Point t){
        return (s.x - t.x)*(s.x - t.x) + (s.y - t.y)*(s.y - t.y);
    }
```
