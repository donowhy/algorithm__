import java.util.*;

class Solution {
    private static final String[] WORDS = {"A", "E", "I", "O", "U"};
    private int index = 0;
    private int answer = 0;

    public int solution(String word) {
        dfs("", word);
        return answer;
    }

    private void dfs(String current, String target) {
        if (current.equals(target)) {
            answer = index;
            return;
        }
        
        if (current.length() == 5) { 
            return;
        }
        
        for (int i = 0; i < WORDS.length; i++) {
            index++;
            dfs(current + WORDS[i], target);
        }
    }

}
