## 前记
算法课上到priority queue，于是把leetcode上对应题目找出来练练手。

## summary
1. stream: use sliding window (k) to save storage
2. kth largest element: use MinHeap instead of MaxHeap, heap size is k

## 1st idea （Wrong）
求 kth largest element，那么build maxHeap. 
step_1: heap利用array storage 需要自己实现：siftDown(), siftUp(); 
step_2: 在KthLargest(int k, int[] nums) 里调用buildHeap(), 多次调用siftUp()
step_3: 在add(int val) 里调用insert(val), 调用1次siftUp()
step_4: 如果k = 1， 返回maximum； if k = 2， 返回root的两个children 较大的一个；if k = 3， 返回root的两个children 较小的一个； if k > 3, how?
由于heap的top元素有最高priority， 期望直接call return heap.peek()返回结果。

以上idea存在的问题和优化：
1. java 有现成的[priority queue api](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html)
   offer(x) Inserts the specified element into this priority queue.
   peek() Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
   poll() Retrieves and removes the head of this queue, or returns null if this queue is empty.
2. 问题：input 为stream，应该采用sliding window节省 storage。sliding window size为 k


## solution 

### use MinHeap or MaxHeap?
1. 假设 k = 3， nums里只有3个元素，那么 3rd largest element即为minimum，期望直接call return heap.peek()返回结果, thus use MinHeap()
2. 假设 k = 3， nums里有4个元素，那么 3rd largest element就不再top位置了？ 怎么办？如果 poll out 4th largest element， 3rd largest element
就又在top位置了，因此限定MinHeap的size 为k
3. 在代码里加print中间结果，每次poll的都是minimum，也验证了是用minHeap
["KthLargest","add","add","add","add","add"] [[3,[4,5,8,2]],[3],[5],[10],[9],[4]]
offer: 4
offer: 5
offer: 8
offer: 2
poll: 2
offer: 3
poll: 3
offer: 5
poll: 4
offer: 10
poll: 5
offer: 9
poll: 5
offer: 4
poll: 4
