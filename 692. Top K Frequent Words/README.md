
## Summary
这个题目是[347. Top K Frequent Elements]()的follow up只是把integer 变为String. 
当frequency相同时，在定义Comparator时有一点改变:  
sorted by frequency from highest to lowest.   
If two words have the same frequency, then the word with the lower alphabetical order comes first.  

```java
// descending order
Collections.sort(ans, 
                 (w1, w2) -> map.get(w1).equals(map.get(w2)? w1.compareTo(w2) : map.get(w2) - map.get(w1)));
```
```java
// ascending order
PriorityQueue<String> minHeap = new PriorityQueue<>(
            (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1): map.get(w1) - map.get(w2));
```

## Solution 1 
**hashMap + Collection sort**  
time complexity: O(N + N*logN) = O(N*logN) . 
space complexity: O(N) of hashMap to store word frequency
```java
     public List<String> topKFrequent(String[] words, int k) {
        if(words == null || words.length == 0){
            return null;
        }
        
        Map<String, Integer> map = new HashMap<>();
        for(String w: words){
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        
        List<String> ans = new ArrayList<>(map.keySet());
        
        Collections.sort(ans, (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w1.compareTo(w2) : map.get(w2) - map.get(w1));
        
        return ans.subList(0, k);
        
    }
```
## Solution 2 
**hashMap + minHeap**  
time complexity: O(N + N*logK) = O(N*logK) . 
space complexity: O(N) of hashMap to store word frequency
```java
   public List<String> topKFrequent(String[] words, int k) {
        if(words == null || words.length == 0){
            return null;
        }
        
        Map<String, Integer> map = new HashMap<>();
        for(String w: words){
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        
        // minHeap to keep top K frequent words
        PriorityQueue<String> minHeap = new PriorityQueue<>(
            (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1): map.get(w1) - map.get(w2));
        
        // O(N*logK)
        for(String w: map.keySet()){
            minHeap.offer(w);
            if(minHeap.size() > k){
               minHeap.poll(); 
            }
        }
            
        //build the ans
        List<String> ans = new ArrayList<>();
        while(!minHeap.isEmpty()){
            ans.add(minHeap.poll());
        }
        
        Collections.reverse(ans);
        return ans;
    }
```
