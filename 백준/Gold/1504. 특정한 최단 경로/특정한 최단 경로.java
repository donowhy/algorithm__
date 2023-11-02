import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 200_000_000; // 최대 n-1개의 간선을 지나는데, 각 간선의 최대 가중치가 1,000,000이므로 충분히 큰 값으로 설정
    static int n, e, v1, v2;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;

    static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        long answer = 0;
        // 1 -> v1 -> v2 -> N
        long route1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n);

        // 1 -> v2 -> v1 -> N
        long route2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n);

        // 두 경로 중 작은 값을 최단 경로로 한다. 단, 경로가 존재하지 않는 경우(INF 이상) -1을 출력한다.
        if (route1 >= INF && route2 >= INF) {
            answer = -1;
        } else {
            answer = Math.min(route1, route2);
        }

        System.out.println(answer);
        br.close();
    }

    static long dijkstra(int start, int end) {
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.vertex]) continue;
            visited[current.vertex] = true;

            for (Node node : graph.get(current.vertex)) {
                if (!visited[node.vertex] && dist[node.vertex] > dist[current.vertex] + node.weight) {
                    dist[node.vertex] = dist[current.vertex] + node.weight;
                    pq.offer(new Node(node.vertex, dist[node.vertex]));
                }
            }
        }

        return dist[end];
    }
}
