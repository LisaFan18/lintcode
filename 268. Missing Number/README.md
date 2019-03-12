**Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.** 
## Summary
这个题目的3，4种解法算是给我开了眼界，不要只局限于常规的解法。

## Idea
1. 这个题目很有意思。第一反应要在range [0, n] 里找missing number，肯定是排序。时间复杂度至少是O(NlogN).  
   special case 1: the missing number is 0  
   special case 2: the missing number is n
2. 利用hashset: 时间复杂度O(N)，空间复杂度O(N)
3. 由于题目的特殊性，我们是知道expected sum，利用Gaussian Formula， expectedSum - actualSum 即为 missing element
4. 由于题目的特殊性，元素和index可以一一对应起来，采用index XOR value loop through all array, 最后的result即为 missing element
## XOR Bit manipulation
```java
   public int missingNumber(int[] nums) {
       int len = nums.length;
       int missing = nums.length; // the maximum of index is len-1, 
       for(int i=0; i< len; i++){
           missing ^= i ^ nums[i]; 
       }
        
       return missing;
    }
```

## Gaussian Formula 
```java
   public int missingNumber(int[] nums) {
       int len = nums.length;
       int expectedSum = len*(len+1)/2;
       
       int actualSum = 0;
       for(int i=0; i<len; i++){
           actualSum += nums[i];
       }
        
        return expectedSum - actualSum;
    }
```
