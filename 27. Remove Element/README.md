## 前记
昨晚睡太晚，早上按点就醒了，醒来就坐在电脑前做这个题目，虽然是easy, 也知道是two pointer，但是写出来的code多于10行了，而且很绕，思维混乱。这说明自己没有把two pointer
的本质搞清楚。

## summary
1. two pointer问题，想清楚，low runner pointer i 和 fast runner pointer j increment的条件。
2. 要坚信easy的题目，就5行左右，否则掉坑里了
3. two pointers: 
   * solution 1 两个pointers 都从左边开始，increment节奏不一致
   * solution 2 两个pointers 分别位于两端，left pointer每次右移1步，right pointer有可能连着左移多步， right pointer身兼两职：作为new length的counter
   又充当了left pointer的角色（移动节奏很混乱，所以代码也很绕）
   * solution 3 简化两个pointers角色，increment条件单一化。

## solution 1
1. 同向 two pointer
```java
class Solution {
    public int removeElement(int[] nums, int val) {  
        int i = 0;
        for(int j=0; j<nums.length; j++){
            if(nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
       
        return i;     
    }
}
```
2. example 1 nums 就变为 [2,2,2,3] returned length is 2 [2,2 | 2,3]
3. 如果target is rare, 会有大量的元素要移动。例如val = 2， 只有nums[0] = 2, nums.length 很大，那么nums里所有的元素要向前移动一位，开销有点大。 
4. 有什么更好的解决方案呢？ 题目说：The order of elements can be changed. It doesn't matter what you leave beyond the new length.

## solution 2 
1. 考虑到nums 在new length 右边的元素其实是废弃的。可以把右边的元素和val 交换，等等，似乎没必要交换。只需把 new length 右边的不等于val的元素去覆盖掉前面的val即可
  盖掉前面的val即可. 
2. 用nums.length 作为 the right one of two pointers 
  如何找到 new length右边不等于val的元素？while (j>i && nums[--j] == val)
  ```java
   class Solution {
    public int removeElement(int[] nums, int val) {  
        int i = 0;
        int j = nums.length; 
        while(i<j){
            if(nums[i] == val){ 
                while (j>i && nums[--j] == val){
                    ;
                }
                nums[i] = nums[j];  
            } 
            i++;
        } 
        return j;     
    }
}  
  ```

## solution 3
solution 2的代码看起来很绕，外层判断while（i<j）里层又判断while（i<j）。
仔细想 left pointer i 右移的条件为nums[i] != val; right pointer j左移的条件为nums[i] == val。 典型的 if-else 结构
```java
class Solution {
    public int removeElement(int[] nums, int val) {  
        int i = 0;
        int j = nums.length; 
        while(i<j){
           if(nums[i] == val){
             nums[i] = nums[--j]; 
           }else{
               i++;
           } 
        }
        return j;     
    }
}
```


