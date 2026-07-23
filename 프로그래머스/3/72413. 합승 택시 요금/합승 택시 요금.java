import java.util.*;

class Solution {

    static class Info {
        int node;
        int cost;

        Info(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    private List<List<Info>> graph;
    private static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];

            graph.get(from).add(new Info(to, cost));
            graph.get(to).add(new Info(from, cost));
        }

        int[] distS = dijkstra(n, s);
        int[] distA = dijkstra(n, a);
        int[] distB = dijkstra(n, b);

        int answer = INF;

        for (int i = 1; i <= n; i++) {
            answer = Math.min(
                answer,
                distS[i] + distA[i] + distB[i]
            );
        }

        return answer;
    }

    private int[] dijkstra(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Info> pq =
            new PriorityQueue<>(Comparator.comparingInt(info -> info.cost));

        dist[start] = 0;
        pq.offer(new Info(start, 0));

        while (!pq.isEmpty()) {
            Info current = pq.poll();

            int currentNode = current.node;
            int currentCost = current.cost;

            // 이미 더 짧은 경로로 처리된 경우
            if (currentCost > dist[currentNode]) {
                continue;
            }

            for (Info next : graph.get(currentNode)) {
                int nextCost = currentCost + next.cost;

                if (nextCost < dist[next.node]) {
                    dist[next.node] = nextCost;
                    pq.offer(new Info(next.node, nextCost));
                }
            }
        }

        return dist;
    }
}