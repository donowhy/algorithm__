import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> {
            return Integer.compare(a[1], b[1]);
        });
        int r = 1;
        int a = routes[0][1];
        
        for(int i=1; i<routes.length; i++) {
            int s = routes[i][0];
            int e = routes[i][1];
            
            if(s > a) {
                r ++;
                a = e;
            }
        }
        
        return r;
    }
}