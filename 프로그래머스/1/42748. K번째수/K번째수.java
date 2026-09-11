import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < commands.length; i++) {
            int s = commands[i][0];
            int e = commands[i][1];
            int c = commands[i][2];
            
            int[] temp = new int[e - s + 1];
            int idx = 0;
            
            for(int j = s - 1; j < e; j++) {
                temp[idx] = array[j];
                idx++;
            }
            
            Arrays.sort(temp);
            
            int result = temp[c - 1];
            answer.add(result);
        }
    
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}