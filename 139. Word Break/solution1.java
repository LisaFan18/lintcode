class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //1. state: boolean f[i] = true, wordDict contains s.substring(0, i) 
        boolean[] f = new boolean[s.length() + 1]; // s is non-empty
        //2. initialize
        f[0] = true;
        for(int i=1; i<=s.length(); i++){
            for(int j=0; j<i; j++){
                //3. state transfer function
                //the left  segmentation: f[j]
                //the right segmentation: s.substring(j, i)
                // two improvements: (remove redundant operations)
                // if f[j] is false, no need to check the right segment
                // if the length of right segment is more than the maximum length 
                if(f[j] && wordDict.contains(s.substring(j, i))){
                    f[i] = true;
                }
            }
        }
        // 4. return 
        return f[s.length()];
    }
}
