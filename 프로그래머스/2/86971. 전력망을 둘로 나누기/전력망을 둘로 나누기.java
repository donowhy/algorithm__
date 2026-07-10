import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        ArrayList<Integer>[] list = new ArrayList[n + 1];
        
        for(int i=0; i<n+1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] a : wires) {
            list[a[0]].add(a[1]);
            list[a[1]].add(a[0]);
        }
        
        int answer = Integer.MAX_VALUE;
        
        for(int i=0; i<wires.length; i++) {
            boolean[] visited = new boolean[n + 1];
            
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            int count = ending(list, v1, v2, visited);
            
            int diff = Math.abs(count - (n - count));
            
            answer = Math.min(answer, diff);
        }
        
        return answer;
    }
    
    int ending(ArrayList<Integer>[] list, int current, int cutNode, boolean[] visited) {
        visited[current] = true;
        int count = 1;
        
        for(int i=0; i<list[current].size(); i++) {
            int next = list[current].get(i);
            
            if (next == cutNode) continue; 
            
            if (visited[next]) continue; 
            
            count += ending(list, next, cutNode, visited);
        }
        
        return count; 
    }
}