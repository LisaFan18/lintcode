## Summary 
1. 网友总结的[sliding window template](https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.) 解决所有substring search问题
2. two pointers + hash map count

## Solution
```java
     public List<Integer> findAnagrams(String s, String p) {
        if(s==null || p == null || p.length() > s.length()){
            return null; 
        }
        
        List<Integer> ans = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for(char c: p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        int begin = 0, end = 0;
        int count = map.size(); // not p.length() as p might contains replicate characters
        
        while(end < s.length()){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0){
                    count--;
                }
            }
            
            end++;
            
            // begin pointer move condition
            while(count == 0){
                char tmpc = s.charAt(begin);
                if(map.containsKey(tmpc)){
                    map.put(tmpc, map.get(tmpc) + 1);
                    if(map.get(tmpc) > 0){
                        count++;
                    }
                    
                }
                if(end - begin == p.length()){
                    ans.add(begin);
                }
                
                begin++;
            }
        }
        
        return ans;       
    }
```
