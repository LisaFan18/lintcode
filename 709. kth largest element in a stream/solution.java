class KthLargest {
    // why use final? final means it will never change once the object is created,
    // it also has some special memory semantics under java memory model
    final PriorityQueue<Integer> minHeap;
    final int k; 
    public KthLargest(int k, int[] nums) {
        this.k = k; 
        minHeap = new PriorityQueue<Integer>(k); 
        for(int n : nums){
            add(n);
        }
    }
    
    public int add(int val) {
        minHeap.offer(val);
        
        // if heap size is greater than k after inserting x, remove the minimum in heap
        if(minHeap.size() > k){
            int i = minHeap.poll(); // remove
        }
        
        return minHeap.peek(); // the top element is the Kth largest elements
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
