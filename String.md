# Summary
1. API:  
* s.charAt(i); char[] arrChar = s.toCharArray(); new String(arrChar)
2. clarify:
* string is null or ""
* contain leading or trailing spaces? 
* a == A? does the string contain only lowercase letter? 

# Problems
## 1. [387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/)
1. Idea:
* 1st pass: go through the string and use a hashmap to count the number of character appears in the string
* 2nd pass: find the index of the first unique character
2. Complexity
* time: O(n) is the best solution. since it ensure that the character is unique you have to check the whole string anyway.
* space: O(n) as we have to keep a hashmap with n element

## 2. [344. Reverse String](https://leetcode.com/problems/reverse-string/)
1. in place: [wiki](https://en.wikipedia.org/wiki/In-place_algorithm) no auxilliary data structure, only use replacement or swapping
2. [printable ASCII characters](https://web.itu.edu.tr/sgunduz/courses/mikroisl/ascii.html): letter, digit, punctation marks 
3. Idea: 
* 直接利用StringBuilder: return new StringBuffer(s).reverse().toString();
* 用双指针left,right: swap即可。但是需要先char[] arrChar = s.toCharArray()。Space complexity is O(n)
