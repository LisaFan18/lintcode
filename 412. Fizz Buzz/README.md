## Summary
1. multiples of three: multiples倍数.  
如何判断num 是不是3的倍数? 余数remainder是否为0. 0%3 == 0； 1%3==1；4%3 == 1；
因此3和5的公倍数即为： i%3==0 && i%5 == 0  

2. String.valueOf(i): int 转换为对应的string。3->“3”  

## Solution
```java
   public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        
        for(int i=1; i<=n; i++){
           if (i%3==0 && i%5 == 0) {
                ans.add("FizzBuzz");
            } else if (i%5==0) {
                ans.add("Buzz");
            } else if (i%3==0) {
                ans.add("Fizz");
            } else {
               ans.add(String.valueOf(i)); 
            } 
        }
        
        return ans;
    }
```
