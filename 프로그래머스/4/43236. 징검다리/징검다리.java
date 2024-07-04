import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canPlace(distance, rocks, n, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canPlace(int distance, int[] rocks, int n, int minDistance) {
        int removed = 0;
        int prevRock = 0;

        for (int rock : rocks) {
            if (rock - prevRock < minDistance) {
                removed++;
                if (removed > n) return false;
            } else {
                prevRock = rock;
            }
        }

        if (distance - prevRock < minDistance) {
            removed++;
        }

        return removed <= n;
    }
}