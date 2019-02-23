## Summary
1. 利用滑动窗口： 减去头部元素，加上尾部元素； 减少中间元素的重复计算
2. int[] nums: nums.**length** not ~~nums.size(), nums.length()~~
3. 函数返回值为 int[], null case应该返回 **return new int[0]** not ~~return null~~

## 2nd solution
1. 改进1: 改变滑动窗口的坐标访问方式。  
滑动窗口： 减去头部元素，加上尾部元素，简单的坐标访问方式应该为：  
``` ans[i] = ans[i-1] - num[i-1] + num[i+k-1] ```
2. 改进2: 1st solution 里变量variable 只用了一次，可以去掉，直接用ans[0]

```java
  public int[] winSum(int[] nums, int k) {
       if(nums == null || nums.length == 0 || nums.length < k ){
           return new int[0]; // not return null
       }
      
       int[] ans = new int[nums.length - k + 1];
       
       // sum 1st window
       for(int i=0; i<k; i++){
          ans[0] += nums[i]; 
       }
       
       // sliding window
       for(int i=1; i<ans.length; i++){
          ans[i] = ans[i-1] - nums[i-1] + nums[i+k-1];
       }
       
       return ans;
    }
```
## 1st solution
```java
   public int[] winSum(int[] nums, int k) {
       if(nums == null || nums.length == 0 || nums.length < k ){
           return new int[0];
       }
       
       int sum = 0, i = 0;
       int len = nums.length;
       int[] ans = new int[len - k + 1];
       
       // sum 1st window
       for(i=0; i<k; i++){
          sum += nums[i]; 
       }
       
       ans[0] = sum;
       // sliding window
       for(i=k; i<len; i++){
          ans[i-k+1] = ans[i-k] - nums[i-k] + nums[i];
       }
       
       return ans;
    }
```




