import java.util.*;

class Solution {

    int[] parents;
    int minNum = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] costs) {
        
        parents = new int[n];
        
        for(int i=0; i<n; i++) {
            parents[i] = i;
        }
        
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        
        int total = 0;
        int edgeCount = 0;
        
        for(int i=0; i<costs.length; i++) {
            int[] cost = costs[i];
            
            if(find(cost[0]) != find(cost[1])) {
                union(cost[0], cost[1]);
                total += cost[2];
                edgeCount ++;
                
                if(edgeCount == n - 1) {
                    return total;
                }
            }
        }
        return 0;
    }
    
    public void union(int x, int y){
        int px = find(x);
        int py = find(y);
        
        if(px != py) parents[px] = py;
    }
    
    public int find(int x) {
        if(parents[x] == x) {
            return x;
        }
        
        parents[x] = find(parents[x]);
        return parents[x];
    }
}