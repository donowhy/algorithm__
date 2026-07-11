import java.util.*;

class Solution {

    private ArrayList<Integer>[] graph;
    private boolean[] visited;

    public int solution(int n, int[][] computers) {

        graph = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        int networkCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i);
                networkCount++;
            }
        }

        return networkCount;
    }

    private void bfs(int start) {

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}