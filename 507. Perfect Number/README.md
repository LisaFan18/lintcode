## Summary
1. 最直观的 iterate 1 to num 逐个check，时间复杂度为O(n)， 得到time limit error
2. 改进策略：[判断N是否是质数，为什么判断到根号N就可以了？](https://leetcode.com/problems/perfect-number/)
   * 首先，约数是成对出现的。比如24,你找到个约数3,那么一定有个约数8,因为24/3=8。
   * 然后，这对约数必须一个在根号n之前，一个在根号n之后。
   * 因为都在根号n之前的话，乘积一定小于n（根号nX根号n=n），
   * 同样，都在根号n之后的话，乘积一定大于n。所以，如果你在根号n之前都找不到约数的话，那么根号n之后就不会有了。(二分法的思想)
3. divisor: 3 is a divisor of 12, because 12 ÷ 3 = 4 exactly
4. factors: Numbers we can multiply together to get another number. 2 and 3 are factors of 6, because 2 × 3 = 6  
A number can have MANY factors! 1, 2, 3, 4, 6 and 12 are all factors of 12

## Solution
```java
 class Solution {
    public boolean checkPerfectNumber(int num) {
        // corner case
        if(num < 0 || num == 1){
            return false;
        }
        
        int sum = 1; // 1 is all number's divisor
        for(int i=2; i*i<=num; i++){
           // i is divisor of num
           if(num%i == 0){
               if(i*i == num){
                  sum += i; // two factors are same
               }else{
                 // two factors must appears in pair; (i, sum/i) are in the left/right of sqrtroot 
                  sum += (i + num/i); 
               }
           } 
           
           // optimize
           if(sum > num){
               return false;
           }
        }
        
        return sum == num;
    }
}
```



   
