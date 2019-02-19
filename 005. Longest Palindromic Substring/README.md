
## Summary
1. 注意区分 [ 516. Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/). 
2. Longest Palindromic Sustring 和 Subsequence的区别：    
* Substring 子串    是continious的。例如"bbbab" 最长substring 是 “bbb”，长度为3.   
* Subsequence 子串可以不是continious的。例如"bbbab" 最长subsequence 是 “bbbb”，长度为4.   

## 1st idea for LP substring
根据palindrome的定义，基于中心点的枚举算法。  
1. odd length: fix a center, expand for both direction for a longer palindrome. 
2. even length: fix two centers, expand for both direction for a longer palindrome. 

## 1st idea for LP subsequence
1. 打算用two pointers来做，if str[l-1] == str[r+1] 移动r pointer else 同时移动l, r 指针。  
问题：一旦左指针移动后，就没法回看left part的characters。 
2. hint1: how can we reuse previousely computed palindrome to compute a larger palindrome?   
再结合longest，用dp试试？ status: f[i]表示到i为止longest 的 palindrome 长度。似乎不好找 status 转移方程。
3. hint2: If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?  
longest palindrome属于一个区间.  
* status应为：f[i][j]    
* 三种子问题, 其status transfer function: f[i][j] = max{f[i+1][j], f[i][j-1], f[i+1][j-1] + 2|s[i] == s[j] }  
* 区间动态规划:按照长度j-i从小到大的顺序去算
* return f[0][n-1]

## Solution for LP substring
```java
  public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        
        int len = s.length();
        int start = 0,  maxLength = 1; 
        
        // i is the center of palindrome
        for(int i=1; i<len; i++){
            
            // palindrome is odd length
            int low = i - 1; 
            int high = i + 1;
            while(low>=0 && high < len && s.charAt(low) == s.charAt(high)){
                if(high - low + 1 > maxLength){
                    start = low;
                    maxLength = high - low + 1;
                }
                
                low--;
                high++;
            }
            
            // palindrome is even length
            low = i - 1; 
            high = i;
            while(low>=0 && high < len && s.charAt(low) == s.charAt(high)){
                if(high - low + 1 > maxLength){
                    start = low;
                    maxLength = high - low + 1;
                }
                
                low--;
                high++;
            }
        }
        
        return s.substring(start, start + maxLength); 
        
    }
```
## Solution for LP subsquence
```java
   public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        
        int len = s.length(); 
        int[][] f = new int[len][len];
        
        for(int i=len-1; i>=0; i-- ){
            f[i][i] = 1; // 'a' (string with length==1) is also a palindrome
            
            for(int j=i+1; j<len; j++){
                if(s.charAt(i) == s.charAt(j)){
                    f[i][j] = f[i+1][j-1] + 2;
                } else {
                    f[i][j] = Math.max(f[i+1][j], f[i][j-1]);
                }
            }
        }
        
        return f[0][len-1];
    }
```
