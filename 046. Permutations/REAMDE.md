  
## Solution
```java
public List<List<Integer>> permute(int[] nums) {
        // global variable
        List<List<Integer>> ans = new ArrayList<>();
        //corner case
        if(nums == null){
            return ans;
        }
        
        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), ans);
        return ans;
    }
    
    public void dfs(int[] nums, boolean[] visited, 
                     List<Integer> permutation,
                     List<List<Integer>> ans ){
        // terminate condition
        if(nums.length == permutation.size()){
            ans.add(new ArrayList<Integer>(permutation));
            return;
        }
        
        //expand 
        for(int i=0; i<nums.length; i++){
            if(visited[i]){
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums,visited, permutation, ans);
            
            //backtrack
            permutation.remove(permutation.size() - 1); // always remove the last in
            visited[i] = false;
        }
        
    }
```
