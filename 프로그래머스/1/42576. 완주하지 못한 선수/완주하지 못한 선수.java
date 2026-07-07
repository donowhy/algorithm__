import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> in = new HashMap<>();
        
        for(int i=0; i<completion.length; i++) {
            in.put(completion[i], in.getOrDefault(completion[i], 0) + 1);
        }
        
        String answer = "";
        
        for(int i=0; i<participant.length; i++) {
            in.put(participant[i], in.getOrDefault(participant[i], 0) - 1);
            int num = in.get(participant[i]);
            if(num == -1) {
                answer = participant[i];
            }
        }
        
        return answer;
    }
}