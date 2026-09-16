import java.util.*;

class Solution {

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        ArrayList<Integer>[] arr = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        // 양방향 그래프
        for (int[] road : roads) {
            int s = road[0];
            int e = road[1];

            arr[s].add(e);
            arr[e].add(s);
        }

        // destination으로부터 각 지역까지 거리
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        queue.offer(destination);
        dist[destination] = 0;

        while (!queue.isEmpty()) {

            int now = queue.poll();

            for (int next : arr[now]) {

                if (dist[next] != -1) {
                    continue;
                }

                dist[next] = dist[now] + 1;
                queue.offer(next);
            }
        }

        int[] answer = new int[sources.length];

        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }

        return answer;
    }
}