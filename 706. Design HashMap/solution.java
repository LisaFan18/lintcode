class MyHashMap {
    class ListNode {
        int key;
        int value;
        ListNode next;
        ListNode(int k, int v, ListNode n){
            this.key = k;
            this.value = v;
            this.next = n;
        }
    }

    ListNode[] arr;
    final int size = 10000;
    private int getIndex(int key){
        // https://docs.oracle.com/javase/8/docs/api/?java/lang/Integer.html
        // return Integer.hashCode(key) % size;
        return key % size;
    }
    
    private ListNode find(ListNode head, int key){
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null && curr.key != key){
            prev = curr;
            curr = curr.next;
        }
        
        return prev;
    }
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        arr = new ListNode[size];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) { 
        int index = getIndex(key);
        ListNode head = arr[index];
        ListNode newNode =  new ListNode(key, value, null);
       
        // No collion
        if(head == null){
            arr[index] = newNode;
            return;
        } else if(head.key == key){
            head.value = value;
        } else {
            ListNode prev = find(head,key);
            // prev.next == null ? prev.next = newNode : prev.next.value = value;
            if(prev.next == null){
                prev.next = newNode; 
            } else {
                prev.next.value = value;
            }
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getIndex(key);
        ListNode head = arr[index];
        
        if(head == null){
            // key does not exist
            return -1;
        } else if (head.key == key) {
            // if key is at the beginning of ll
            return head.value;
        } else {
            ListNode prev = find(head, key);
            return prev.next == null ? -1: prev.next.value;
        }
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getIndex(key);
        ListNode head = arr[index];
        
        if(head == null){
            // key does not exist
        } else if (head.key == key) {
            // if key is at the beginning of ll
            arr[index] = head.next;
        } else {
            ListNode prev = find(head, key);
            // prev.next == null ? return : prev.next = prev.next.next;
            if(prev.next != null){
                prev.next = prev.next.next;
            }
        } 
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
