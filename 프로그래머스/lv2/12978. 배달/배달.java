import java.util.*;

class Solution {
    
    int[] minTime;  // Array to store minimum time to reach each city.
    
    public int solution(int N, int[][] road, int K) {
        int[][] time_map = new int[N + 1][N + 1];
        minTime = new int[N + 1];
        Arrays.fill(minTime, Integer.MAX_VALUE);  // Initialize minTime with maximum value.

        for (int i = 0; i < road.length; i++) {
            if (time_map[road[i][0]][road[i][1]] != 0) {
                time_map[road[i][0]][road[i][1]] = Math.min(time_map[road[i][0]][road[i][1]], road[i][2]);
                time_map[road[i][1]][road[i][0]] = Math.min(time_map[road[i][1]][road[i][0]], road[i][2]);
            } else {
                time_map[road[i][0]][road[i][1]] = road[i][2];
                time_map[road[i][1]][road[i][0]] = road[i][2];
            }
        }
        
        dfs(1, 0, time_map, K);  // Start DFS from city 1 with time 0.
        
        int count = 0;
        for (int time : minTime) {
            if (time <= K) count++;  // Count the cities that can be delivered to within time K.
        }
        return count;
    }
    
    void dfs(int city, int time, int[][] time_map, int K) {
        if (time > K || time >= minTime[city]) return;  // If current time exceeds K or is not the minimum, stop traversing.
        minTime[city] = time;  // Update the minimum time to reach this city.

        for (int i = 1; i < time_map[city].length; i++) {
            if (time_map[city][i] != 0) {
                dfs(i, time + time_map[city][i], time_map, K);  // Traverse to the next city.
            }
        }
    }
}
