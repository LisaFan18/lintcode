```java
   public List<Integer> topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return null;
        }
        
        //1. compute frquency
        Map<Integer, Integer> map = new HashMap();
        for(int num : nums){
            // if(map.containsKey(num)){
            //     map.put(num, map.get()+1);
            // }else{
            //     map.put(num, 1);
            // }
            // getOrDefault(key, defalutValue); //defalutValue if key is not found
            map.put(num, map.getOrDefault(num,0)+1);
        }
        
        //2. keep K top frequent elements in minHeap
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((num1,num2)-> map.get(num1) - map.get(num2));
        for(int num:map.keySet()){
            minHeap.add(num);
            if(minHeap.size()>k){
                minHeap.remove();
            }
        }
        
        //3. build the ans
        List<Integer> ans = new ArrayList();
        while(!minHeap.isEmpty()){
            ans.add(minHeap.remove());
        }
        
        //4. reverse
        Collections.reverse(ans);
        return ans;
    }
```
