## 前记
这个题是Easy高频，从今天FB家面试来看，好好准备Easy题目很有必要。为了接下来s家的电面，特地刷一下。

## Summary
1. StringBuilder sb = new StringBuilder(int capacity)
2. sb.append() instead of add()
3. return sb.reverse().toString() instead of sb.reverse() as it is an StringBuilder 

## idea
根据题目描述即可得到思路
1. toUpperCase()
2. backward processing: reverse()

## Solution
```java
public String licenseKeyFormatting(String S, int K) {
        S = S.toUpperCase().replaceAll("-", "");
        
        StringBuilder sb = new StringBuilder(len + K);
        int j=0;
        for(int i=S.length()-1; i>=0; i--){
            if(j==K){
               sb.append("-");
                j=0;
            }
            
            sb.append(S.charAt(i)) ;
            j++;
        }
        
        return sb.reverse().toString();
 }
```
