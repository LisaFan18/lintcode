## 思考
题目描述很简单，一句话 Given a string, find the length of the longest substring without repeating characters.
“longest”： 需要一个counter来记录 maxLength。
“substring” ：two pointers记录 substring的start 和 end。双指针移动条件是什么？
“without repeating characters” 可以得出需要使用 key - value 来判断是否重复。key：character， value：position
 
 Question: 何时出现一个substring candidates？ 
 当前扫描的字符已经出现在hashMap里, 并且 pos >= left pointer.  pos >= left pointer这个条件很重要。例如            
 abba
 ^^^
leftPointer = 0， 扫描到第二个b是发现，下面hashmap里已经存在b了，更新leftPointer = 1 + 1； 
            
|character|position|
|---------|--------|
| a | 0|
| b | 1|

abba
^^^^
leftpointer = 2

|character|position|
|---------|--------|
| a | 0|
| b | 2|
扫描到第二个a是发现，下面hashmap里已经存在a了，更新leftPointer = 0 + 1 ? WRONG! 因为此时hashmap里的a在leftpointer的左边，其实是废弃的。

## solution 2 （cleaner）
1.  char[] sarr = s.toCharArray(); 直接用s.charAt(i)
2.  用Math.max() 代替 maxLength = (maxLength < (j - i)) ? (j - i) : maxLength; 
3.  代码maxLength = (maxLength < (j - i)) ? (j - i) : maxLength; 出现两次，合并
4.  if(hashMap.containsKey(sarr[j]) && hashMap.get(sarr[j]) >= i){i = hashMap.get(sarr[j]) + 1;} 简化为：i = Math.max(i, hashMap.get(sarr[j]) + 1);

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 2) return s.length();
        
        int maxLength = 0;
        Map<Character, Integer> hashMap = new HashMap<>();
        
        for(int i=0, j=0; j<s.length(); j++){
            if(hashMap.containsKey(s.charAt(j))){
                i = Math.max(i, hashMap.get(s.charAt(j)) + 1);
            }   
            
            hashMap.put(s.charAt(j), j);
            maxLength = Math.max(maxLength, j - i + 1);
        }
        return maxLength; 
    }
}
```

## solution 1
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n < 2){
            return n;
        }
        
        char[] sarr = s.toCharArray();
        int i=0, j=0, maxLength = 0;
        Map<Character, Integer> hashMap = new HashMap<>();
        
        for(j=0; j<n; j++){
            if(hashMap.containsKey(sarr[j]) && hashMap.get(sarr[j]) >= i){
                maxLength = (maxLength < (j - i)) ? (j - i) : maxLength; 
                i = hashMap.get(sarr[j]) + 1;
            }   
            
            hashMap.put(sarr[j], j);
        }
        return (maxLength < (n - i)) ? (n - i) : maxLength; 
    }
}
```
