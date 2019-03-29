# 前记
这是打算用github来整理leetcode上刷过题目的第一个，一上来就遇到个dynamic programming （dp）的题目，搞了很久。

# Summary
1. DP问题，首先找子问题。
2. 在找子问题时，这个问题不只1个子问题。于是采用for loop穷举所有子问题。  

# ？思考：如何从题目的描述看出这个题目应该用dp来解决？
try：划分型dp题目有什么特点？

# Example 1:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true  
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
# Example 2:
Input: s = "applepen", wordDict = ["a", "p", "ap”, “ple", "apple", "pen"]
Output: true   
Explanation: Return true because "applepen" can be segmented as "apple pen", "ap ple pen", "a p ple pen".
            Note that more than one ways to segment s
# solution v1
读完题目没思路，看到tag为dp，于是套用dp的解决思路：
## 1. 确定状态
   f[i]代表前i个字符串是否在wordDict里   
   最后一步：  
       如果s[0, n-1]在wordDict里，check s[n-1, n]是否在字典里  
       如果s[0, n-2]在wordDict里，check s[n-2, n]是否在字典里  
       如果s[0, n-3]在wordDict里，check s[n-3, n]是否在字典里  
       ....  
   子问题：  
      原问题：s[0,n] 是否可以由wordDict里的单词拼出来，  
      n个子问题:  s[0,n-1],  s[0,n-2],  s[0,n-3] 是否可以由wordDict里的单词拼出来.
   
## 2. 转移方程
```java
      for (j = 0; j< i; j++)
          f[i] == True AND wordDict.contains(s.substring(j, i))
```
## 3. 初始条件和边界情况  
Corner example: str='a', wordDict=['a']  
      f[0] = true
      in example 2, f[1] = f[0] && wordDict.contains("a"), f[1] must be true, so f[0] should be true
## 4. 计算顺序
      f[0], f[1], f[2],...

#1 Solution 2
solution 1 有3处冗余操作:  
1）if left segment is false, no need to check the right segment;   
2) if right segment's length is greater than the maxLength of wordDict  
3) if f[i] 已经有一种分割是true，no need to check the rest possible segements.
```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // null case
        if(s == null || wordDict.size() == 0){
            return false;
        }
        
        int maxWordLength = 0;
        for(String str: wordDict){
            maxWordLength = str.length() > maxWordLength? str.length() : maxWordLength;
        }
        
        //1. state: boolean f[i] = true, wordDict contains s.substring(0, i) 
        boolean[] f = new boolean[s.length() + 1]; // s is non-empty
        //2. initialize
        f[0] = true;
        for(int i=1; i<=s.length(); i++){
            
            // number of subproblems: i 
            for(int j=0; j<i; j++){
                //3. state transfer function
                //the left  segmentation: f[j]
                //the right segmentation: s.substring(j, i)
                
                // 3 improvements: (remove redundant operations)
                // improve 1: if f[j] is false, no need to check the right segment
                if(!f[j]){
                    continue;
                }
                
                // improve 2: if the length of right segment is more than the maximum length 
                if(i-j > maxWordLength){
                    continue;
                }
                
                if(f[j] && wordDict.contains(s.substring(j, i))){
                    f[i] = true;
                    //improve 3:wordDict={"a", "bc", "ab", "c"}  
                    // when "abc"="a" + "bc"; return true, no need to check: "abc"="ab" + "c";
                    break;
                }
            }
        }
        // 4. return 
        return f[s.length()];
    }
}
```
