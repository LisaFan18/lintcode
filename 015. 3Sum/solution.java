class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> rs = new ArrayList<>();
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length - 2; i++){
            // make sure unique triplets
            if(i>0 && nums[i] == nums[i-1]){
               continue; 
            }
            
            int l=i+1, r=nums.length - 1;
            int sum = 0 - nums[i];
            while(l < r){
                if(nums[l] + nums[r] == sum){
                   rs.add(Arrays.asList(nums[i], nums[l], nums[r])); 
                   l++;
                   r--;
                   // skip the same element Eg. [0, -5, -5, -5, 5, 5, 5]
                   while(l<r && nums[l] == nums[l - 1]){
                       l++;
                   }
                   while(r>l && nums[r] == nums[r + 1]){
                       r--;
                   }
                } else if (nums[l] + nums[r] < sum) {
                    l++;
                } else {
                    r--;
                }
            }
        }
      return rs;
    }
}
