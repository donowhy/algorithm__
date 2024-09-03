import java.util.*;

class Solution {

    ArrayList<Node>[] graph;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 1. 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 2. 그래프 구축
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            // 양방향 그래프
            graph[u].add(new Node(v, 1));
            graph[v].add(new Node(u, 1));
        }

        // 3. 목적지에서 모든 노드까지의 최단 거리 계산
        int[] distFromDestination = dijkstra(destination, n);

        // 4. 각 소스에서 목적지까지의 거리 결과 저장
        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            result[i] = distFromDestination[sources[i]] == Integer.MAX_VALUE ? -1 : distFromDestination[sources[i]];
        }

        return result;
    }

    // 목적지에서 모든 노드까지의 최단 거리 계산
    private int[] dijkstra(int start, int n) {
        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int nowVertex = currentNode.index;

            if (check[nowVertex]) continue;
            check[nowVertex] = true;

            // 연결된 정점들에 대해 거리 갱신
            for (Node next : graph[nowVertex]) {
                if (dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        return dist;
    }

    class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
