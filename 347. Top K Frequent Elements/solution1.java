```java
   public List<Integer> topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return null;
        }
        
        //1. count frquency
        Map<Integer, Integer> map = new HashMap();
        for(int num : nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }
        
        //2. Collections.sort()
        List<Integer> ans = new ArrayList<>(map.keySet());
        
        // in descending order
        Collections.sort(ans, (k1, k2) -> map.get(k2) - map.get(k1));
        
        //3. java.util.List subList(int fromIndex, int toIndex) toIndex exclusive
        return ans.subList(0, k);
    }
```
