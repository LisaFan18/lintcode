## 思考
这个题目很简单，就是MergeSort 里的merge方法。不过，merge不是in-place的。题目说了nums1 有足够的空间，那么充分利用nums1即可，无需extra memory.
从后往前merge。

## summary
1. solution 1 two pointers 从左往右移，需要extra space
2. solution 2 two pointers 从右往左移，无需extra space，减少i>=0时的冗余移动
3. solution 3 利用 for替换while， short form for java if-else statement，精简上面的代码

## solution 1 O(m+n) space
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m+n];
        int i=0, j=0, k=0;
        while(i<m && j<n){
           if(nums1[i] <= nums2[j]){
              tmp[k++] = nums1[i++]; 
           }else{
              tmp[k++] = nums2[j++]; 
           }
        }
        
        while(i<m){
            tmp[k++] = nums1[i++]; 
        }
        
        while(j<n){
            tmp[k++] = nums2[j++];  
        }
        
        // copy back to nums1
        for(k=0; k<m+n; k++){
           nums1[k] = tmp[k]; 
        }
    }
}
```

## solution 2 O(1) space
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k=m+n-1, i=m-1, j=n-1;
        while(i>=0 && j>=0){
           if(nums1[i]>nums2[j]){
              nums1[k--] = nums1[i--];
           }else{
              nums1[k--] = nums2[j--];
           }
        }
        
        while(j>=0){
            nums1[k--] = nums2[j--]; 
        }   
    }
}
```

## solution 3 (cleaner version)
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        for(int k=m+n-1, i=m-1, j=n-1; k>=0; k--){
            if(i>=0 && j>=0){
                nums1[k] = (nums1[i] > nums2[j]) ? nums1[i--]:nums2[j--];
            }else if(j>=0){
                nums1[k] = nums2[j--]; 
            }
        }
    }
}
```
