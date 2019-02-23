## Summary
1. Priority自定义Comparator，多层compare方式
2. int compare(Object o1, Object o2) Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

## Solution 
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
