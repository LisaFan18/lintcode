class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        
        int ans = nums[0] + nums[1] + nums[nums.length-1]; 
        for(int i=0; i+2<nums.length; i++){
           int l = i+1, r = nums.length - 1;
           int sum = 0;
           while(l < r){  
               sum = nums[i] + nums[l] + nums[r];
               if(sum == target){
                   return target;
               } 
               
               if (sum < target) {
                   l++;
               } else {
                   r--; 
               } 
               // 必须在while里，因为每次指针移动就会更新sum。需要有一个全局变量，来保存closest target的sum值。必须穷尽所有的triplets来得到global 最优解
               if(Math.abs(target - sum ) < Math.abs(target - ans)){
                    ans = sum;
               }  
           } 
        }    
        return ans;
    }
}
