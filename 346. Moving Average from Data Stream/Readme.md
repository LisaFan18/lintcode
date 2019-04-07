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
```java
class MovingAverage {
    private int[] queue; 
    private int sum;
    private int count;
    private int head;
    private int tail;
    /** Initialize your data structure here. */
    public MovingAverage(int s) {
        queue = new int[s];
        sum = 0;
        count = 0;
        head = -1;
        tail = -1;
    }
    
    public double next(int val) {
        sum += val;
        count++;
        
        if(count - 1 == queue.length ){
           // dequeue
           head = (head + 1) % queue.length;
           sum -=  queue[head];
           count--;
        }
        tail = (tail + 1) % queue.length;
        queue[tail] = val;
        return (double)sum/count;
    }
}
```
