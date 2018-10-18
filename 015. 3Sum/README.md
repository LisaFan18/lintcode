## 思考
1. brute-force 3层for loop，O(n^3) 太暴力，没难度。肯定要超时
2. 从题干出发：1） “unique”， 每次找到a,b,c candidates 用hashmap check是否重复；2）先用hashmap 把nums扫描一遍。再用2层for loop O(n^2) 
   结果需要保持2份：hashmap 和 return list<list>
3. 如果array有序，可以通过comparision排除repeative items。如果有序，two pointers可以从两端向中间靠拢搜索,再加上外面一层for loop O(n^2)。预处理排序需要O(nlgn)。total O(n^2)

## summary
1. 保持unique方案：有序或hashmap
2. 程序技巧
```java
 while(l < r){
     // 先处理要return的结果
     if(nums[l] + nums[r] == sum){...}
 }    
```
3. 常用API, 
```java
List<List<Integer>> rs = new ArrayList<>();
Arrays.sort(nums);

rs.add(Arrays.asList(nums[i], nums[l], nums[r]));
```
