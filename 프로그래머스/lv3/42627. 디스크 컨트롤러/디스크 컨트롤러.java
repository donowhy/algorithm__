import java.util.*;

public class Solution {
    public int solution(int[][] jobs) {
        int answer = 0, now = 0, i = 0, len = jobs.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        while (i < len || !queue.isEmpty()) {
            while (i < len && jobs[i][0] <= now) {
                queue.offer(jobs[i++]);
            }

            if (!queue.isEmpty()) {
                int[] curr = queue.poll();
                now += curr[1];
                answer += now - curr[0];
            } else {
                now = jobs[i][0];
            }
        }

        return answer / len;
    }
}
