## Summary
利用java 8的新特性，可以compact的形式解决： 
0. Character.isLetter(c) // English Alphabeta means 26 letters a-zA-Z
1. clean string  
```java
String[] words = str.replaceAll("\\pP", " ").toLowerCase().split("\\s+"); // p
```
2. **Max/Min <K, V> by value**
```java
Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
```
[Collections.max/min(Collection, Comparator) API](https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html)
Returns the maximum element of the given collection, according to the order induced by the specified comparator.    

3. **Sort <K,V> by value and output Top 5 key** 
```java
    List<String> ans = new ArrayList<>();
		// ascending
		countMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.forEach(entry -> ans.add(entry.getKey()));
		// descending 
		countMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.forEach(entry -> ans.add(entry.getKey()));
    // Top 5 key
    return ans.subList(0, 5);
```

## Idea
[LintCode 1369](https://www.lintcode.com/problem/most-common-word/description)
1. use hashMap<key, count> 来统计 Most common
2. clean input string  
   * 过滤掉所有非letter字符  
   * toLowerCase()  
   * split("\\s+"): separated by a space  
3. iterate on splitted String[], 并利用hashmap count
5. iterate on hashmap to find out maxcount

## Solution (Cleaner Version)
```java
  public String mostCommonWord(String str, String[] banned) {
        Set<String> ban = new HashSet<> (Arrays.asList (banned));
        
        Map<String, Integer> countMap = new HashMap<>();
        
        String[] words = str.replaceAll ("\\pP", " ").toLowerCase().split ("\\s+");
        
        for (String w : words) {
            if (!ban.contains (w)) {
                countMap.put (w, countMap.getOrDefault (w, 0) + 1);
            }
        }
        return Collections.max (countMap.entrySet(), Map.Entry.comparingByValue()).getKey(); 
    }
```

## Solution (Ituitive Version)
```java
   private static String mostFreqWord(String str, List<String> blackList) {
		String maxString = null;
		if(str == null || str.length() == 0) {
			return maxString;
		}
		
		String[] words = cleanString(str);
		Map<String, Integer> countMap = new HashMap<>();
		for (String w : words) {
			if (!blackList.contains(w)) {
				countMap.put(w, countMap.getOrDefault(w, 1) + 1);
			}
		}
		
		// update max
		int maxCount = 0;
		for(Map.Entry<String, Integer> entry: countMap.entrySet()) {
			if (entry.getValue() > maxCount) {
				maxCount = entry.getValue();
				maxString = entry.getKey();
			}
		}
		
		return maxString;
	}
  
  private static String[] cleanString(String str) {
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			if (!Character.isLetter(c)) {
				// if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
				sb.append(' ');
			} else {
				sb.append(c);
			}
		}
		return sb.toString().toLowerCase().split("\\s+");
	}
```

## Solution (return List<String>)
```java
private static List<String> mostFreqWord2(String str, List<String> blackList) {
		List<String> ans = new ArrayList<>();
		if(str == null || str.length() == 0) {
			return ans;
		}
		
		String[] words = str.replaceAll("\\pP", " ").toLowerCase().split("\\s+"); 
		
		Map<String, Integer> countMap = new HashMap<>();
		for (String w : words) {
			if (!blackList.contains(w)) {
				countMap.put(w, countMap.getOrDefault(w, 1) + 1);
			}
		}
		
		// Sort <K,V> by value in descending  order
		countMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.forEach(entry -> ans.add(entry.getKey()));
		
		// top 5
		return ans.subList(0, 5);
	}
```
