## 前记
这个题目4年前刷过，再次看到时有印象用到了hashmap，但具体细节记不清了。看来背答案是行不通的。

## Summary
1. 配对问题数据结构用 key-value
2. corner case: throw new IllegalArgumentException("no two sum solution");
3. return new int[]{-1, -1}
4. // Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
   Map<Integer, Integer> hm = new HashMap<>(); // reduced version
5. int[] nums; nums.length instead of length(). 区分技巧： length()是方法，属于class； 而nums是array，length是属性

## solution 
 leetcode给出了很完善的[solution](https://leetcode.com/problems/two-sum/solution/)
 
### 1st idea_brute force O（n^2）
外层for 扫描 a[i], 内层for check target - a[i] 是否在 array里。time complexity  O（n^2）。显然这方法不行。

### 2nd two pass hashMap
题目本质是在找配对，先找X，再找Y，使得 X + Y = target。既然要找，就得遍历，遍历的过程中，记录什么呢？这是思考的核心问题。记录的东西，可以让后续处理时间复杂度能减少到最低，最好是O(1)。

“配对” 问题 显然是要用key-value这种数据结构了。
题目对element和index 感兴趣，那显然key，value应该记录这两项。可是，谁来做key，谁做value呢？既然要配对的是element，说明要
对element进行查找，因此element为key

第一遍 pass，hashMap.put(nums[i], i)
第二遍 pass，complement= target - nums[i], 如果hashMap.containsKey(complement) 配对成功。
      注意：题目要求you may not use the same element twice. 因此要check hashMap.containsKey(complement) != i

### one pass hashMap
在往hashMap里put的同时，也look back a[i]的complement是否已经在hashMap里了，如果在，说明已经找到。因为题目说hashMap.containsKey(complement)

