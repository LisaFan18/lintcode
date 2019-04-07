## summary 
1. 数据的处理顺序是FIFO，所以用queue

## solution 1 (build-in Queue)
```java
class MovingAverage {
    private Queue<Integer> queue; 
    private int sum;
    private int count;
    private int size;
    /** Initialize your data structure here. */
    public MovingAverage(int s) {
        queue = new LinkedList<>();
        sum = 0;
        count = 0;
        size = s;
    }
    
    public double next(int val) {
        sum += val;
        count++;
        
        if(queue.size() == size ){
           sum -= queue.poll();
           count--;
        }
        queue.offer(val);
        return (double)sum/count;
    }
}
```

## solution 2 (circular array)
// to do
