## 思考
题目要求 in-place，所以利用数组超出size之外的element无效的特点，采用后面的元素覆盖前面重复的元素。故采用two pointers，且为同向双指针
left指针为result数组的长度， right指针遍历，遇到相同元素，右移。
指针移动条件：
     right指针遇到相同元素，右移，不同元素停下；
     array[left + 1] = array[right]


## summary
1. Easy 级别的题目一般5行搞定，如果你的回答太长，考虑优化


## solution
```java
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        if(nums.length == 1){
            return 1;
        }
        
        int l = 0, r = 1;
        while( l<=r && r < nums.length){
            if(nums[l] != nums[r]){
                nums[++l] = nums[r];
            }
            r++;
        }
        
        return l + 1;     
    }
```

## cleaner code
1. 结构nums.length == 0 , return 0 重复出现
2. left pointer 一定小于 right pointer，冗余判断
3. leetcode solution 没有判断 input为null
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;      
        if(len < 2){
            return len; 
        }
        
        int l = 0, r = 1;
        while(r < len){
            if(nums[l] != nums[r]){
                nums[++l] = nums[r];
            }
            r++;
        }
        
        return l + 1;     
    }
}
```
