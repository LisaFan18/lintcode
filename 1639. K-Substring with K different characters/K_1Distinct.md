## Summary
1. follow up question: K-SubString with K - 1 different characters.   
2. **example**:    
input： awaglk, 4， output: awag  
input： wawaglknagagwunagkwkwagl, 4， output: awag, naga, gagw, gkwk, wkwa  
3. 

## 1st Idea
K-SubString with K different characters 使用 Set<Character> dict 来判断是否有重复元素出现。  
K-SubString with K - 1 different characters 能否 Set<Character> dict 来判断是否有重复元素出现 + counter的形式呢？     
```java
        
        if (dict.contains(str.charAt(j)) && counter == 1) {
					break;
				}
				// only 1 element appears two times.
				if (dict.contains(str.charAt(j))) {
					counter++; 
				} else {
					dict.add(str.charAt(j));
				}
				j++;
```
问题来了，counter 应该在何时 reset呢？ 在移动left的指针的时候吗？  
```java
      
      if (j - i == k) {
				ans.add(str.substring(i, j));
			}

			dict.remove(str.charAt(i));
			counter = 0; //???
			i++;
```
当重复元素是sliding window 中间的元素时会失效. 例如 “abbcb” k=4   
**Thus, 需要有一个dictionary 来记录 character和对应的count。可以是hashmap，也可以是 int[] dict = new int[256]**

## 2nd Idea
1. 使用 int[] dict = new int[256] 作为dictionary来记录 character和对应的count。 
2. 移动右边指针 dict[str.charAt(j)]++;  
   移动左边指针 dict[str.charAt(i)]--;   
3. 指针移动条件：  
  * 右指针：所有element都不同，或者重复元素最多1个。  
  * 左指针：sliding window == k； 重复元素 >= 2个。  
4. 何时出现 valid的结果？ j-i == k && dict[j-i] counter == 2只有1个，其他counter == 1.
5. counter 何时++， 何时--？  
  * dict[j] == 2 时 counter++
  * 用Set记录重复元素
  * 移动左边指针时，如果str.charAt(i) 时重复元素，counter--；

## solution 
```java
  public static List<String> findK_1Distinct(String str, int k) {
		List<String> ans = new ArrayList<>();
		if (str == null || str.length() < k) {
			return ans;
		}
		
		int counter = 0;
		int[] dict = new int[26];
		Set<Character> repeatChar = new HashSet<>();
		
		int i = 0, j = 0;
		while (i <= str.length() - k) {
			while (j - i < k) {
				char c = str.charAt(j);
				int index = c - 'a';
				dict[index]++;
				// character is repeated more than twice 
				if(dict[index] > 2) {
					break;
				}
				
				if(dict[index] == 2) {
					counter++;
					repeatChar.add(c);
				}
				j++;
			}
			// valid substring is founded
			if (j - i == k && counter == 1 ) {
				ans.add(str.substring(i, j));
			}
			
			char left = str.charAt(i); 
			if(repeatChar.contains(left)) {
				counter--;
				repeatChar.remove(left);
			}
			
			dict[left - 'a']--;
			i++;
		}

		return ans;
	}
```
