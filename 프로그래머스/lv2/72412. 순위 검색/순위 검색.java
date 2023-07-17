import java.io.*;
import java.util.*;

class Solution {
    private Map<String, List<Integer>> infoMap = new HashMap<>();
    private static final int LANG = 3, LOC = 2, YEAR = 1, FOOD = 0;

    public int[] solution(String[] info, String[] query) {
        // prepare info map
        for (String s : info) {
            String[] split = s.split(" ");
            int score = Integer.parseInt(split[4]);
            for (int i = 0; i < (1 << 4); i++) {
                StringBuilder key = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    if ((i & (1 << j)) > 0) key.append(split[j]);
                    else key.append("-");
                }
                infoMap.computeIfAbsent(key.toString(), x -> new ArrayList<>()).add(score);
            }
        }

        // sort each list for binary search
        for (Map.Entry<String, List<Integer>> entry : infoMap.entrySet()) 
            entry.getValue().sort(null);

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] split = query[i].split(" ");
            String key = split[0] + split[2] + split[4] + split[6];
            int score = Integer.parseInt(split[7]);
            List<Integer> list = infoMap.getOrDefault(key, new ArrayList<>());
            int s = 0, e = list.size();
            while (s < e) {
                int mid = (s + e) / 2;
                if (list.get(mid) < score) s = mid + 1;
                else e = mid;
            }
            answer[i] = list.size() - s;
        }

        return answer;
    }
}
