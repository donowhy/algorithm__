import java.util.*;

class Solution {
    public int solution(int[] elements) {
        
        HashSet<Integer> nums = new HashSet<>();
        
        for(int i=0; i<elements.length; i++) {
            dfs(elements, 0, i, nums, elements.length, i);
        }
        
        int answer = nums.size();
        return answer;
    }
    
    public int dfs(int[] es, int sum, int current, HashSet<Integer> nums, int amount, int f) {
        if(current == f + amount) {
            return sum;
        }
        
        sum += es[current % amount];
        nums.add(sum);
        dfs(es, sum, current + 1, nums, amount, f);
    
        return sum;
    }
}