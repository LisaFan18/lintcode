## Summary
1. [java.util.Arrays.sort()](https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html) This class contains various methods for manipulating arrays (such as sorting and searching).
2. java.util.ArrayList.contains() 
3. 去除重复元素：
```java
            // 跳过重复元素
            if(i>0 && nums[i] == nums[i-1] && !visited[i-1]){
                continue;
            }
```
**Note:** 
  * 必须加上条件 !visited[i - 1] 不影响当前dfs的执行，没有这个条件ans始终为[]
  * use !visited[**i-1**] not !visited[i]
   

## Idea
1. 1st ide: 在permuation问题上，每次往ans里add之前，利用ArrayList.contains()判断是否已经存在。 但这种方法效率不高，因为重复元素，重复call dfs
2. 2nd： 先对nums排序，再在call dfs之前判断是否跟前一个元素相等。

## Solution
```java
   public List<List<Integer>> permuteUnique(int[] nums) {  
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return ans;
        }
        Arrays.sort(nums);
            
        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), ans);
        return ans;
    }
    
    public void dfs(int[] nums, boolean[] visited, 
                    List<Integer> permutation,
                   List<List<Integer>> ans){
        // terminate
        if(permutation.size() == nums.length){
            ans.add(new ArrayList(permutation)); 
            return;
        }
        
        for(int i=0; i<nums.length; i++){
           
            if(visited[i]){
                continue;
            }
            // 跳过重复元素
            if(i>0 && nums[i] == nums[i-1] && !visited[i-1]){
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            
            dfs(nums,visited,permutation,ans);
            
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
        
    }
```
