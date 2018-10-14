# 前记
这是打算用github来整理leetcode上刷过题目的第一个，一上来就遇到个dynamic programming （dp）的题目，搞了很久。

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
      子问题:  s[0,n-1] 是否可以由wordDict里的单词拼出来，
   
## 2. 转移方程
      for (j = 0; j< i; j++)
          f[i] = f[j] AND wordDict.contains(s.substring(j, i))
## 3. 初始条件和边界情况
      f[0] = true
      in example 2, f[1] = f[0] && wordDict.contains("a"), f[1] must be true, so f[0] should be true
## 4. 计算顺序
      f[0], f[1], f[2],...

#待提高
 solution v1 有两处冗余操作






