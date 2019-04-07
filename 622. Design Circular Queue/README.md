# Summary
1. 算法设计的不好，实现起来会很绕。
2. solution 1 不好的原因：  
* 函数之间依赖太大例如dequeue/enqueue跟isFull/Empty
* head和tail不仅充当了index的功能，还利用他们的相对位置来计算isFull/Empty，同一个变量干了两件事情，所以逻辑很绕，corner case太多，容易出错。
* 解决方案：再引入一个变量len:
  * Queue is empty when len==0; Queue is full when len==k; 
  * how to change len: len++ when enqueue; len-- when dequeue

# Solution 2 (good)
## Idea
1. 利用head = 0, tail = -1, len = 0 三个variable。   
  **注意:** head的初始值是0不是-1. 如果是-1，when we do enqueue() first and then call front(), since head == -1, arr[head] will throw out index error  
2. 利用 head = (head + 1)% arr.length， tail = (tail + 1)% arr.length 实现circular array

```java
class MyCircularQueue {
    private int[] arr;
    private int head;
    private int tail; 
    private int len;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.arr = new int[k];
        this.head = 0;
        this.tail = -1;
        this.len = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        tail = (tail + 1) % arr.length;
        arr[tail] = value;
        len++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        head = (head + 1) % arr.length;
        len--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        
        return arr[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return arr[tail];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return len == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return len == arr.length;
    }
}
```

# Solution 1 (bad)
## Idea
1. 利用head = -1, tail = -1, size = k 三个variable。
2. 先实现isEmpty, isFull。因为会被其他function调用：  
  * 当queue为空时，head和tail的index为-1; 
  * 利用head和tail的下标来计算isEmpty
  * 利用head和tail, size的关系来计算isFull. 这种方式很笨拙，要考虑很多特殊情况容易出错。
3. 利用head和tail的相对位置来计算isFull/Empty。由于是circular array，所以不能简单的利用head>tail来判断isFull/Empty。
## Code
```java
class MyCircularQueue {
    private int[] arr;
    private int head;
    private int tail; 
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.size = k;
        this.arr = new int[k];
        this.head = -1;
        this.tail = -1;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        
        if(head == -1 && tail == -1){
           head = 0;  
        }  
        if (tail == size - 1) {
           tail = -1; 
        } 
        
        arr[++tail] = value;
        return true;
        
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        //if queue has only 1 element, after this deQueue() operation, queue will be empty
        if(head == tail){
            head = -1;
            tail = -1;
        } else {
            if(head == size - 1){
                head = -1;
            }
            head++;
        }     
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        
        return arr[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return arr[tail];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (head == -1) && (tail == -1);
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
       int tmpTail = tail;
       if(tail == size - 1){
           tmpTail = -1;
       }
       return tmpTail + 1 == head;
    }
}
```
