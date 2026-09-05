import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> count = new HashMap<>();

        for (String[] cloth : clothes) {
            String type = cloth[1];
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        int answer = 1;

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            answer *= entry.getValue() + 1;
        }

        return answer - 1;
    }
}