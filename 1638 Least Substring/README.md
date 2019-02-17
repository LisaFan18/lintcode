The problem is from [lintcode 1638](https://www.lintcode.com/problem/least-substring/description?_from=ladder&&fromId=69) 

## Summary
1. 当分多种情况讨论时，先考虑最简单的情况。不然容易把自己绕晕。以此题为例，只有在 s.charAt(i-1) == s.charAt(i) && cnt < k 
这两个条件同时满足的情况下，不用generate new substring. 
2. coding tips:  
  * 比较相邻pairs 用s.charAt(i-1) == s.charAt(i) 比 s.charAt(i+1) == s.charAt(i) 程序更简洁  
  * 计数器初始化为1而不是0，程序更简洁


## Solution
根据题意, 同时满足以下两个条件时， 就不用generate new substring：
the letter in the substring should be same, -> s.charAt(i) == charAt(i-1)
and the number of letters in the substring does not exceed k --> cnt < k

public int getAns(String s, int k) {
       // extreme case 
        if(s == null || s.length() == 0 || k == 0){
            return 0;
        }

        int rs=1, cnt=1, len = s.length();
        for(int i=1; i < len; i++){
           if(s.charAt(i-1) == s.charAt(i) && cnt < k){
               cnt++;
           } else {
               rs++; // generate a new substring
               cnt=1; // re-initialize the counter
           }
        }

        return rs;
    }
