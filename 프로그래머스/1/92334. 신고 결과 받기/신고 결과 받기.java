import java.util.*;

class Solution {

    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> indexMap = new HashMap<>();
        Map<String, Integer> reportCount = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            indexMap.put(id_list[i], i);
        }

        Set<String> uniqueReports = new HashSet<>(Arrays.asList(report));

        for (String record : uniqueReports) {
            String[] names = record.split(" ");
            String reported = names[1];

            reportCount.put(
                reported,
                reportCount.getOrDefault(reported, 0) + 1
            );
        }

        int[] answer = new int[id_list.length];

        for (String record : uniqueReports) {
            String[] names = record.split(" ");

            String reporter = names[0];
            String reported = names[1];

            if (reportCount.get(reported) >= k) {
                answer[indexMap.get(reporter)]++;
            }
        }

        return answer;
    }
}