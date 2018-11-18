## Summary

0. 难点：element为负数时怎么办？例如[2, -3, -4], return 24

1. corner case: 
     input只有一个元素时，例如[-2], return -2; 
2. status: 
     * f[i] 以a[i]结尾的连续子序列的maximum 乘积 
     * g[i] 以a[i]结尾的连续子序列的minimum 乘积 
     
3. transfer function:

     if a[i] > 0
     
        f[i] = Max{a[i], f[i-1]*a[i]} // 例如[2, 0 , 3], f[3] = a[3]
        
        g[i] = Min{a[i], g[i-1]*a[i]}
        
     
     if a[i] < 0 
     
        f[i] = Max{a[i], g[i-1]*a[i]} // 例如[2, 0 , 3], f[3] = a[3]
        
        g[i] = Min{a[i], f[i-1]*a[i]}
 ## Solution      
  ```java
    
    public int maxProduct(int[] nums) {
        int n = nums.length; 
        int[] f = new int[n]; // maximum subarray ending at a[i]
        int[] g = new int[n]; // minimum subarray ending at a[i]; a[i] < 0
        
        f[0] = g[0] = nums[0];
        int max = nums[0];
       
        for(int i=1; i<n; i++){
            if(nums[i] > 0){
                f[i] = Math.max(nums[i], f[i-1]*nums[i]);
                g[i] = Math.min(nums[i], g[i-1]*nums[i]);
            } else {
                f[i] = Math.max(nums[i], g[i-1]*nums[i]);
                g[i] = Math.min(nums[i], f[i-1]*nums[i]);
            }
            
            max = Math.max(max, f[i]);
        }
        
        return max;
    }

  ```
     
