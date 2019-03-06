## Summary
0. sliding window
1. ```Set<Character> s = new HashSet();```
2. str.length() not ~~size()~~ 
3. str.toCharArray() not ~~tocharArray()~~
4. int i = 0, j = 0; not ~~int i = j = 0;~~
5. 区分 *Substrings and subsequences*  
Substrings are *consecutive* subsequences. For example, for sequence *abc* we have  
 * substrings: a, ab, abc, b, bc, c, and the empty substring;  
 * subsequences: a, b, ab, c, ac, bc, abc, and the empty subsequence.  

## 2nd idea 
1. solution 1 有重复操作，sliding window 移动前后，变化的只有window 两端的elements，window中间的元素不变。
2. 双指针问题，要考虑清楚，left, right 指针移动的条件  
* 左指针移动条件：遇到重复元素时，   从charSet里面 remove重复元素  
* 右指针移动条件：遇到非重复元素时， 往charSet里面  add 新元素; right - left 长度达到K
3. 改进： 相对solution1，节省了重复调用函数isUnique的开销；减少sliding window中间元素的重复操作
4. Time: O(n) as each character is only be visited once. 

## Solution 2
```java
   public int KSubstring(String stringIn, int K) {
        if(stringIn == null || K == 0) {
            return 0;
        }
       int len = stringIn.length();
       
       Set<String> ans = new HashSet();
       Set<Character> charSet = new HashSet();
       
       int i = 0, j = 0;
       while ( i <= len - K){
         while(j - i < K){
             char c = stringIn.charAt(j);
             if(charSet.contains(c)) {
                break;
             }
             // move right pointer j
             charSet.add(c);
             j++;
         }
         
         if(j - i == K) {
            ans.add(stringIn.substring(i, j));
         }
         
         //move left pointer i
         charSet.remove(stringIn.charAt(i));
         i++;
       }
       
       return ans.size();
    }
```
## 1st idea 
1. time: O(nk), space: O(nk)
2. create two hashset: one set to store Character; one set to store subString
3. i start index, check every substring(i, i+K) whether it contains K distinct characters.

## Solution 1(Memory Limit Exceeded)
```java
public int KSubstring(String stringIn, int K) {
        if(stringIn == null || K == 0) {
            return 0;
        }
       int len = stringIn.length();
       
       Set<String> ans = new HashSet();
       for(int i=0; i<=len - K; i++){
           String  sub = stringIn.substring(i, i+K); 
           if(isUnique(sub)){
               ans.add(sub);
           }
       }
       return ans.size();
    }
    
    public boolean isUnique(String str){
        Set<Character> rs = new HashSet();
        for(char c: str.toCharArray()){
            if(rs.contains(c)) {
                return false;
            }
            
            rs.add(c);
        }
        
        return true;
    } 
}
```
