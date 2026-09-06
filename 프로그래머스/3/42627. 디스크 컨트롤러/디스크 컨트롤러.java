import java.util.*;

class Solution {

    public int solution(int[][] jobs) {

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int currentTime = 0;
        int totalTime = 0;
        int idx = 0;
        int count = 0;

        while (count < jobs.length) {

            while (idx < jobs.length &&
                   jobs[idx][0] <= currentTime) {

                pq.offer(jobs[idx]);
                idx++;
            }

            if (!pq.isEmpty()) {

                int[] job = pq.poll();

                currentTime += job[1];

                totalTime += currentTime - job[0];

                count++;

            } else {
                currentTime = jobs[idx][0];
            }
        }

        return totalTime / jobs.length;
    }
}