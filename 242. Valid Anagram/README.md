## Summary
1. 最简单，直接利用java.util.Arrays的sort 和equals 方法
2. 利用hashMap count：  
   2.1 利用java 自带的HashMap  
     * 3 passes + O(2N) space: 1st map1++, 2nd map2++, 3rd: check map1.get() - map2.get() == 0
     * 2 passes + O(N)  space: 1st map1++, map1--;  2nd: check map1.get() != 0
   2.2 用数组int[] count = new int[256]
     count[c - '0']++

## [Solution 1](https://leetcode.com/problems/valid-anagram/solution/ )

## Solution 2
