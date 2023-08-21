import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        
        int res = 0;
        
        for(int i=1; i<triangle.length; i++){
            int temp = 0;
            while(temp != i){
                for(int j = temp; j < temp + 2; j++){
                    dp[i][j] = Math.max(dp[i-1][temp] + triangle[i][j], dp[i][j]);
                    res = Math.max(dp[i][j], res);
                }
                temp++;
                
            }
        }
        // System.out.println(Arrays.deepToString(dp));

        return res;
    }
}