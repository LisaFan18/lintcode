
class Solution {
    public int coinChange(int[] coins, int m) {
        // 1. amount i can be made up by f[i] combinations 
        int[] f = new int[m + 1];
        // initialization
        f[0] = 0;
        //for(int i=0; i<m + 1; i++)
        for(int i=1; i<=m; i++){
            f[i] = Integer.MAX_VALUE;
        }
        
        for(int j=0; j<=m; j++){  
            for(int k=0; k<coins.length; k++){
                int pre = j-coins[k];
                if(pre >=0 && f[j] > f[pre]){
                   f[j] = f[pre] + 1; 
                }
            }
        }
        
        if(f[m] == Integer.MAX_VALUE){
            return -1;
        } 
        
        return f[m]; 
    }
}

